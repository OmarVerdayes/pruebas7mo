package utez.edu.mx.joyeria.extraUtiles;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@NoArgsConstructor
@Getter
@Setter
public class ActionsFiles {

    public String saveFile(MultipartFile archivoOriginal, String ruta) throws IOException {

        if(archivoOriginal.isEmpty()){
            return ruta+"defecto";
        }

        String nombreArchivoOriginal = archivoOriginal.getOriginalFilename();

        // Verifica si el archivo con el mismo nombre ya existe en la carpeta
        int contador = 0;
        String rutaCompleta;
        File archivo;
        do {
            contador++;
            rutaCompleta = ruta + generateUniqueName(nombreArchivoOriginal, contador);
            archivo = new File(rutaCompleta);
        } while (archivo.exists());

        //guardamos el archivo
        byte[] bytes = archivoOriginal.getBytes();
        Path path = Paths.get(rutaCompleta);
        Files.write(path, bytes);

        //devolvemos la ruta del archivo
        return String.valueOf(path);

    }
    public String generateUniqueName(String nombreBase, int contador) {
        // Genera un nombre único agregando el contador antes de la extensión del archivo
        int puntoIndex = nombreBase.lastIndexOf(".");
        if (puntoIndex != -1) {
            String nombreSinExtension = nombreBase.substring(0, puntoIndex);
            String extension = nombreBase.substring(puntoIndex);
            return nombreSinExtension + "_" + contador + extension;
        } else {
            return nombreBase + "_" + contador;
        }
    }

    public void deleteFile(String ruta){
        File file=new File(ruta);
        if (file.exists()){
            file.delete();
        }
    }

}
//