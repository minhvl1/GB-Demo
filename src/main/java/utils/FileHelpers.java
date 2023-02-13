
package utils;

import org.apache.commons.io.FileDeleteStrategy;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

public class FileHelpers {

    public static void writeTxtFile(String filepath, String text) {
        try {
            File file = new File(filepath);
            while (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(text + "\n" + "\n");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readTxtFile(String filepath) {
        try {
            File f = new File(filepath);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            fr.close();
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readLineTxtFile(String filepath, int line) {
        List<String> lines;
        String value;
        try {
            lines = Files.readAllLines(new File(filepath).toPath());
            value = lines.get(line);
            return value;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getLastFileModified(String directoryFilePath)
    {
        File dir = new File(directoryFilePath);
        File max = null;
        for (File file : dir.listFiles()) {
            if (file.isDirectory() && (max == null || max.lastModified() < file.lastModified())) {
                max = file;
            }
        }

        return String.valueOf(max);
    }

    public void cleanAllureReportFiles(){
        try {
//            String workingDir = System.getProperty("user.dir");
            String pathFolderallure = "allure-results";
            File fileAllure = new File(pathFolderallure);
            File[] listOfFilesAllure = fileAllure.listFiles();
            System.out.println("......................"+pathFolderallure);
            for(int i = 0; i < listOfFilesAllure.length; i++){
                if(listOfFilesAllure[i].isFile()){
                    new File(listOfFilesAllure[i].toString()).delete();
                }
            }
            System.out.println("================ DELETE ALLURE================");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void cleanExtentReportFiles(){
        try {
//            String workingDir = System.getProperty("user.dir");
            String pathFolerExtent="ExtentReports/";
            File fileExtent = new File(pathFolerExtent);
            File[] listOfFilesExtent = fileExtent.listFiles();

            System.out.println("count extent: "+ listOfFilesExtent.length);
            if(listOfFilesExtent.length>2){
                for(int i = 0; i <listOfFilesExtent.length-2 ; i++){
                    if(listOfFilesExtent[i].isDirectory()){
                        FileDeleteStrategy.FORCE.delete(new File(listOfFilesExtent[i].toString()));
                    }
                }
            }


            System.out.println("================ DELETE EXTENT================");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
