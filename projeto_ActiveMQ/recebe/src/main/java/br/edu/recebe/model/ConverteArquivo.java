package br.edu.recebe.model;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ConverteArquivo {

   
 public void ConverteParaArquivo(Reserva reserva, String caminho){
	
	  try {
        FileOutputStream saveFile = new FileOutputStream(caminho);
        ObjectOutputStream stream = new ObjectOutputStream(saveFile);

         // salva o reserva
        stream.writeObject(reserva);

        stream.close();
      } catch (Exception exc) {
        exc.printStackTrace();
      }
	
  }

public Reserva ConverteParaObjeto(String caminho){
	
	 Reserva reserva = null;
     
     try {
            FileInputStream restFile = new FileInputStream(caminho);
            ObjectInputStream stream = new ObjectInputStream(restFile);

            // recupera o reserva
            reserva = (Reserva) stream.readObject();

            stream.close();
     } catch (Exception e) {
            e.printStackTrace();
     }

     return reserva;
	
}


}
