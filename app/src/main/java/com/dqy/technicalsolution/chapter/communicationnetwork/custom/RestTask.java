package com.dqy.technicalsolution.chapter.communicationnetwork.custom;

import android.content.ContentValues;
import android.net.http.HttpResponseCache;
import android.os.AsyncTask;
import android.provider.Settings;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

public class RestTask extends AsyncTask<Void,Integer,Object> {
    private static final String TAG = "RestTask";

    public interface ResponseCallback{
        public void onRequestSuccess(String response);

        public void onRequestError(Exception error);
    }

    public interface ProgressCallback{
        public void onProgressUpdate(int progress);
    }

    private HttpURLConnection mConnection;
    private String mFormBody;
    private File mUploadFile;
    private String mUploadFileName;
    //Activity回调
    private WeakReference<ResponseCallback> mResponseCallback;
    private WeakReference<ProgressCallback> mProgressCallback;

    public RestTask(HttpURLConnection connection){
        mConnection = connection;
    }

    public void setmFormBody(List<ContentValues> formData){
        if(formData == null){
            mFormBody=null;
            return;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < formData.size();i++){
            ContentValues content = formData.get(i);
            for (Map.Entry<String, Object> entry : content.valueSet()){
                sb.append(URLEncoder.encode(entry.getKey()));
                sb.append("=");
                sb.append(URLEncoder.encode(entry.getValue().toString()));
                if(i != (formData.size()-1)){
                    sb.append("&");
                }
            }
        }
        mFormBody = sb.toString();

    }

    public void setUploadFile(File file,String fileName){
        mUploadFile = file;
        mUploadFileName = fileName;
    }

    public void setResponseCallback(ResponseCallback callback){
        mResponseCallback = new WeakReference<ResponseCallback>(callback);
    }

    public void setProgressCallback(ProgressCallback callback){
        mProgressCallback = new WeakReference<ProgressCallback>(callback);
    }

    private void writeMultipart(String boundary, String charset, OutputStream output,boolean writeContent) throws IOException {
        BufferedWriter writer = null;
        try{
            writer = new BufferedWriter(new OutputStreamWriter(output, Charset.forName(charset)),8192);
            //
            if(mFormBody != null){
                writer.write("--" + boundary);
                writer.write("\r\n");
                writer.write("Content-Disposition: form-data; name=\"parameters\"");
                writer.write("\r\n");
                writer.write("Content-Type: text/plain;charset=" + charset);
                writer.write("\r\n");
                writer.write("\r\n");
                if(writeContent){
                    writer.write(mFormBody);
                }
                writer.write("\r\n");
                writer.flush();
            }

            writer.write("--" + boundary);
            writer.write("\r\n");
            writer.write("Conent-Disposition:form-data; name=\"" + mUploadFileName + "\";filename=\""
            + mUploadFile.getName() + "\"");
            writer.write("\r\n");
            writer.write("Content-Type: "+ URLConnection.guessContentTypeFromName(mUploadFile.getName()));
            writer.write("\r\n");
            writer.write("Content-Transfer-Encoding: binary");
            writer.write("\r\n");
            writer.write("\r\n");
            writer.flush();
            if(writeContent){
                InputStream input = null;
                try{
                    input = new FileInputStream(mUploadFile);
                    byte[] buffer = new byte[1024];
                    for(int length = 0;(length = input.read(buffer))>0;){
                        output.write(buffer,0,length);
                    }
                    output.flush();
                }catch (IOException e){
                    Log.w(TAG,e);
                }finally {
                    if(input !=null){
                        try{
                            input.close();
                        }catch (IOException e){

                        }
                    }
                }
            }
            writer.write("\r\n");
            writer.flush();

            writer.write("--" + boundary + "--");
            writer.write("\r\n");
            writer.flush();
        }finally {
            if(writer != null){
                writer.close();
            }
        }
    }

    private void writeFormData(String charset,OutputStream output)throws IOException{
        try {
            output.write(mFormBody.getBytes(charset));
            output.flush();
        }finally {
            if(output != null){
                output.close();
            }
        }
    }
    @Override
    protected Object doInBackground(Void... params) {
        String boundary = Long.toHexString(System.currentTimeMillis());
        String charset = Charset.defaultCharset().displayName();

        try {
            if(mUploadFile != null){
                mConnection.setRequestProperty("Content-Type","multipart/form-data;boundary="+ boundary);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                writeMultipart(boundary,charset,bos,false);
                byte[] extra = bos.toByteArray();
                int contentLength = extra.length;
                contentLength += mUploadFile.length();
                if(mFormBody != null){
                    contentLength += mFormBody.length();
                }
                mConnection.setFixedLengthStreamingMode(contentLength);
            }else if(mFormBody !=null){
                //
                mConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded:charset=" + charset);
                mConnection.setFixedLengthStreamingMode(mFormBody.length());
            }
            mConnection.connect();
            if(mUploadFile != null){
                OutputStream out = mConnection.getOutputStream();
                writeMultipart(boundary,charset,out,true);
            }else if(mFormBody != null){
                OutputStream out = mConnection.getOutputStream();
                writeFormData(charset,out);
            }

            //
            int status = mConnection.getResponseCode();
            if(status >= 300){
                String message = mConnection.getResponseMessage();
                return new InterruptedException(status + "" +message);
            }
            InputStream in = mConnection.getInputStream();
            String encoding = mConnection.getContentEncoding();
            int contentLength = mConnection.getContentLength();
            if(encoding == null){
                encoding = "UTF-8";
            }
            byte[] buffer = new byte[1024];
            int length = contentLength > 0 ? contentLength :0;
            ByteArrayOutputStream out = new ByteArrayOutputStream(length);

            int downloadedBytes = 0;
            int read;
            while ((read = in.read(buffer)) !=  -1){
                downloadedBytes += read;
                publishProgress((downloadedBytes * 100) /contentLength);
                out.write(buffer,0,read);
            }
            return new String(out.toByteArray(),encoding);

        }catch (IOException e){
            Log.w(TAG,e);
            return e;
        }finally {
            if(mConnection != null){
                mConnection.disconnect();
            }
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        if(mProgressCallback != null && mProgressCallback.get() != null){
            mProgressCallback.get().onProgressUpdate(values[0]);
        }
    }

    @Override
    protected void onPostExecute(Object result) {
        if(mResponseCallback != null && mResponseCallback.get() != null){
            if(result instanceof  String){
                mResponseCallback.get().onRequestSuccess((String) result);
            }else if (result instanceof  Exception){
                mResponseCallback.get().onRequestError((Exception) result);
            }else {
                mResponseCallback.get().onRequestError(new IOException("Unknown Error Contacting Host"));
            }
        }
    }
}
