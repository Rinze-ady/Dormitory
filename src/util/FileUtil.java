package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 文件工具类
 */
public class FileUtil {
    /**
     * 拷贝文件
     * @param sourceFilePath 源文件路径
     * @param targetDirPath 目标文件所在目录
     * @return 重命名文件名
     */
    public static String copyFile(String sourceFilePath,String targetDirPath){
        //重命名文件名
        String fileName=System.currentTimeMillis()+sourceFilePath.substring(sourceFilePath.lastIndexOf("."));
        InputStream in=null;
        OutputStream out=null;
        try {
            in=new FileInputStream(sourceFilePath);
            out=new FileOutputStream(targetDirPath+"/"+fileName);//把源文件拷入指定目录
            byte[] by=new byte[1024];
            int len=0;
            while ((len=in.read(by)) != -1){
                out.write(by,0,len);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return fileName;
    }
}
