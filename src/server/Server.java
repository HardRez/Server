
package server;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

  
    public static void main(String[] args) throws IOException {
       ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(4444); // Dinlenecek olan port. 300 de kullanılabilir
            System.out.println("Bağlantı Sağlandı");
        } catch (IOException ex) {
            System.out.println("Hatalı Port Numarası ");
        }
        //Bağlantı için gerekli nesneler
        Socket socket = null;
        InputStream in = null;
        OutputStream out = null;

        try {
            socket = serverSocket.accept();
            System.out.println("Bağlantı Sağlandı");
        } catch (IOException ex) {
            System.out.println("İstemci bağlantısını kabul edemiyor.");
        }

        try {
            in = socket.getInputStream();
        } catch (IOException ex) {
            System.out.println("Soket giriş akışı alınamıyor. ");
        }

        try {
            out = new FileOutputStream("C:\\Users\\hardrez\\Desktop\\client\\yeniDosya.rar"); // Alınacak Dosya İçin Yeni Dosya Oluşturuluyor
        } catch (FileNotFoundException ex) {
            System.out.println("Dosya Bulunamadı ");
        }

        byte[] bytes = new byte[16 * 1024];

        int count;
        while ((count = in.read(bytes)) > 0) { //Okuma işlemi bitinceye kadar çalış
            out.write(bytes, 0, count);
        }

        out.close();
        in.close();
        socket.close();
        serverSocket.close();
    }
    
}
