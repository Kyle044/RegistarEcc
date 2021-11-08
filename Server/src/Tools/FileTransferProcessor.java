package Tools;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class FileTransferProcessor {
    Socket socket;
    InputStream is;
    FileOutputStream fos;
    BufferedOutputStream bos;
    int bufferSize;


    public FileTransferProcessor(Socket client) {
        socket = client;
        is = null;
        fos = null;
        bos = null;
        bufferSize = 0;

    }

    public void receiveFile(String fileName) {
        try {
            is = socket.getInputStream();
            bufferSize = socket.getReceiveBufferSize();
      
            fos = new FileOutputStream(fileName);
            bos = new BufferedOutputStream(fos);
            byte[] bytes = new byte[bufferSize];
            int count;
            while ((count = is.read(bytes)) >= 0) {
                bos.write(bytes, 0, count);
            }
            bos.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendFile(File file) {

        FileInputStream fis;
        BufferedInputStream bis;
        BufferedOutputStream out;
        byte[] buffer = new byte[8192];
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            out = new BufferedOutputStream(socket.getOutputStream());
            int count;
            while ((count = bis.read(buffer)) > 0) {
                out.write(buffer, 0, count);

            }
            out.close();
            fis.close();
            bis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void send(ArrayList<File>files){

        try {
            DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            System.out.println(files.size());
    //write the number of files to the server
            dos.writeInt(files.size());
            dos.flush();

            //write file names 
            for(int i = 0 ; i < files.size();i++){
                dos.writeUTF(files.get(i).getName());
                dos.flush();
            }

            //buffer for file writing, to declare inside or outside loop?
            int n = 0;
            byte[]buf = new byte[4092];
            //outer loop, executes one for each file
            for(int i =0; i < files.size(); i++){

                System.out.println(files.get(i).getName());
                //create new fileinputstream for each file
                FileInputStream fis = new FileInputStream(files.get(i));
                dos.writeLong(files.get(i).length());
                //write file to dos
                while((n =fis.read(buf)) != -1){
                    dos.write(buf,0,n);
                    dos.flush();

                }
                //should i close the dataoutputstream here and make a new one each time?
            }
            //or is this good?
     
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
    
    public void receive(String directory){


        try {
            DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
    //read the number of files from the client
            int number = dis.readInt();
            ArrayList<File>files = new ArrayList<File>(number);
            System.out.println("Number of Files to be received: " +number);
            //read file names, add files to arraylist
            for(int i = 0; i< number;i++){
                File file = new File(dis.readUTF());
                files.add(file);
            }
            int n = 0;
            byte[]buf = new byte[4092];

            //outer loop, executes one for each file
            for(int i = 0; i < files.size();i++){

                System.out.println("Receiving file: " + files.get(i).getName());
                //create a new fileoutputstream for each new file
                FileOutputStream fos = new FileOutputStream(directory+"//" +files.get(i).getName());
                long fileSize = dis.readLong();
                //read file
                while (fileSize > 0 && (n = dis.read(buf, 0, (int)Math.min(buf.length, fileSize))) != -1)
                {
                  fos.write(buf,0,n);
                  fileSize -= n;
                }
                fos.close();
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }


    }

}