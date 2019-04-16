/**
 *
 */
package com.hai.javase.file.util;

import org.junit.Test;

import java.io.*;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 * @author Administrator
 */
public class FileUtil {

    private static final String EMPTY_STRING = "";

    public static List<String> getFilePath(List<String> list, File file) {
        File[] files = file.listFiles();
        if (files.length > 0) {
            for (File f : files) {
                if (f.isDirectory()) {
                    getFilePath(list, f);
                } else {
                    list.add(f.getAbsolutePath().replace("\\", "/"));
                }
            }
        }
        return list;
    }

    /**
     * 创建索引文件
     *
     * @param rootPath
     * @param indexFilePath
     */
    public static void createIndexFile(String rootPath, String indexFilePath) {
        List<String> list = new ArrayList<>();
        getFilePath(list, new File(rootPath));
        StringBuilder sb = new StringBuilder();
        for (String name : list) {
            sb.append(name).append("\r\n");
        }

        try {
            FileWriter writer = new FileWriter(indexFilePath);
            writer.write(sb.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //网络copy
    public static void copyFile(URI srcUri, URI targetUri) throws IOException {
        copyFile(new File(srcUri), new File(targetUri));
    }

    public static void copyFile(String srcUri, String targetUri) throws IOException {
        copyFile(new File(srcUri), new File(targetUri));
    }

    //copy single file
    public static void copyFile(File src, File target) throws IOException {
        src.setReadable(true);
//        target.setWritable(true);
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(src));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(target));
        byte[] buffered = new byte[1024 * 5];
        int len;
        while ((len = bis.read(buffered)) != -1) {
            bos.write(buffered, 0, len);
        }
        bos.flush();
        bos.close();
        bis.close();
    }

    public static void copyDictionary(String src, String target) throws IOException {
        copyDictionary(new File(src), new File(target));
    }

    public static void copyDictionary(File src, File target) throws IOException {
        File[] files = src.listFiles();
        if (!target.exists()) {
            target.mkdir();
        }
        for (File file : files) {
            File currTarget = new File(target.getAbsolutePath(), file.getName());
            if (file.isFile()) {
                copyFile(file, currTarget);
            } else {
                currTarget.mkdir();
                copyDictionary(file, currTarget);
            }
        }
    }

    @Test
    public void testCopyDictionary() throws IOException {
        FileUtil.copyDictionary("C:\\Users\\Administrator\\Downloads\\BT", "C:\\Users\\Administrator\\Downloads\\BT\\BT-5");
    }

    public static void unzipFileWithAnt(String zipFile, String targetFile) throws IOException {
        unzipFileWithAnt(new File(zipFile), new File(targetFile));
    }

    //unzipfile org.apache.ant:ant
    public static void unzipFileWithAnt(File zipFile, File targetFile) throws IOException {

        org.apache.tools.zip.ZipFile zpf = new org.apache.tools.zip.ZipFile(zipFile, "UTF-8");
        Enumeration<org.apache.tools.zip.ZipEntry> entries = zpf.getEntries();

        while (entries.hasMoreElements()) {
            org.apache.tools.zip.ZipEntry entry = entries.nextElement();
            File file = new File(targetFile, entry.getName());
            if (entry.isDirectory()) {
                file.mkdir();
            } else {
                File parent = new File(file.getParent());
                if (!parent.exists()) {
                    parent.mkdirs();
                }
                if (!file.exists()) {
                    file.createNewFile();
                }

                InputStream is = zpf.getInputStream(entry);
                InputStreamReader reader = new InputStreamReader(is, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(reader, is.available());

                FileOutputStream fos = new FileOutputStream(file);
                OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");

//                BufferedOutputStream bos = new BufferedOutputStream(fos, is.available());
//                byte[] bufferd = new byte[1024 * 5];
//                int len;
//                while ((len = is.read(bufferd)) != -1) {
////                    fos.write(bufferd, 0, len);
////                    bos.write(bufferd, 0, len);
//                    osw.write(new String(bufferd));
//                }
//                fos.flush();
//                bos.flush();
//                fos.close();
//                bos.close();
//                is.close();

                char[] bufferd = new char[1024 * 5];
                int len;
                while ((len = bufferedReader.read(bufferd)) != -1) {
                    osw.write(bufferd, 0, len);
                }
                fos.flush();
                osw.flush();
                fos.close();
                osw.close();
                is.close();
            }
        }
        zpf.close();
    }

    @Test
    public void testUnzipFile() throws IOException {
        unzipFileWithAnt("C:\\Users\\Administrator\\Downloads\\BT.zip", "C:\\Users\\Administrator\\Downloads\\BT-2");
    }

    //TODO un finish
    public static void rarFile(String folderPath, String rarPath) throws IOException {
        List<String> filePaths = new ArrayList<>();
        File folder = new File(folderPath);
        getFilePath(filePaths, folder);

        File listFile = File.createTempFile("listFile", ".tmp", folder.getParentFile());
        StringBuilder fileList = new StringBuilder();
        for (String path : filePaths) {
            fileList.append(path).append("\n");
        }

        FileOutputStream fos = new FileOutputStream(listFile);
        fos.write(fileList.toString().getBytes());
        fos.close();

        File rarFile = new File(rarPath);
        String commond = "rar a " + rarFile.getPath() + "@" + listFile.getPath();
        Process process = Runtime.getRuntime().exec(commond.toString() + "\n");
        process.getOutputStream().close();
    }

    //TODO un finish
    public static void unRarFile(String rarPath, String unRarPath) {
        File rarFile = new File(rarPath);
        File target = new File(unRarPath);

        if (!target.exists()) {
            target.mkdirs();
        }

        String commond = "rar x -r " + rarPath + "@" + unRarPath + " /y";
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Process process = Runtime.getRuntime().exec(commond);
                    process.getOutputStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Test
    public void testRarFile() throws IOException {
        rarFile("C:\\Users\\Administrator\\Downloads\\BT", "C:\\Users\\Administrator\\Downloads\\BT.rar");
    }

    @Test
    public void testUnRarFile() throws IOException {
        unRarFile("C:\\Users\\Administrator\\Downloads\\BT.rar", "C:\\Users\\Administrator\\Downloads\\BT-2");
    }

    /**
     * 根据指定的字符重命名文件
     *
     * @param pathname
     * @param replaceString
     */
    public static void renameFileBySpecificString(String pathname, String replaceString) {
        renameFileBySpecificString(pathname, replaceString, EMPTY_STRING, FileReplacePosition.CONTAINS);
    }

    /**
     * 根据指定的字符重命名文件
     *
     * @param pathname
     * @param replaceString
     * @param newString
     */
    public static void renameFileBySpecificString(String pathname, String replaceString, String newString) {
        renameFileBySpecificString(pathname, replaceString, newString, FileReplacePosition.CONTAINS);
    }

    /**
     * 根据指定的字符重命名文件
     *
     * @param pathname
     * @param replaceString
     * @param fileReplacePosition
     */
    public static void renameFileBySpecificString(String pathname, String replaceString, FileReplacePosition fileReplacePosition) {
        renameFileBySpecificString(pathname, replaceString, EMPTY_STRING, fileReplacePosition);
    }

    /**
     * 根据指定的字符重命名文件
     *
     * @param pathname
     * @param replaceString
     * @param newString
     * @param fileReplacePosition
     */
    public static void renameFileBySpecificString(String pathname, String replaceString, String newString, FileReplacePosition fileReplacePosition) {
        File file = new File(pathname);
        if (null == file || !file.exists()) {
            System.out.println("要重命名的文件\"" + pathname + "\"不存在！");
            return;
        }

        if (null == fileReplacePosition) {
            fileReplacePosition = FileReplacePosition.START;
        }

        newString = null == newString ? EMPTY_STRING : newString.trim();

        List<File> allFiles = new ArrayList<>();
        List<File> hiddenFiles = new ArrayList<>();
        List<File> newFiles = new ArrayList<>();

        boolean isDirectory = file.isDirectory();
        if (isDirectory) {
            File[] files = file.listFiles();
            for (File f : files) {
                allFiles.add(f);
                if (f.isFile()) {
                    if (f.isHidden()) {
                        hiddenFiles.add(f);
                        continue;
                    }
                    File newFile = fileRename(f.getPath(), replaceString, newString, fileReplacePosition, f);
                    addNewFile(newFile, newFiles);
                }
            }
        } else {
            allFiles.add(file);
            if (!file.isHidden()) {
                File newFile = fileRename(pathname, replaceString, newString, fileReplacePosition, file);
                addNewFile(newFile, newFiles);
            } else {
                hiddenFiles.add(file);
            }
        }

        printFileRenameResult(pathname, replaceString, fileReplacePosition, allFiles, hiddenFiles, newFiles, isDirectory);

    }

    /**
     * 控制台打印文件重命名的结果
     *
     * @param pathname
     * @param replaceString
     * @param fileReplacePosition
     * @param allFiles
     * @param hiddenFiles
     * @param newFiles
     * @param isDirectory
     */
    private static void printFileRenameResult(String pathname, String replaceString, FileReplacePosition fileReplacePosition, List<File> allFiles,
                                              List<File> hiddenFiles, List<File> newFiles, boolean isDirectory) {
        System.out
                .println("本次" + (isDirectory ? "文件夹下的文件" : "文件") + "重命名共扫描文件 " + allFiles.size() + " 个。其中包含未做操作的隐藏文件 " + hiddenFiles.size() + " 个。");
        if (newFiles.size() > 0) {
            System.out.println("成功重命名的 " + newFiles.size() + " 个文件如下：");
            for (File newFile : newFiles) {
                System.out.println(newFile.getPath());
            }
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            if (isDirectory) {
                stringBuilder.append("要重命名的文件夹 \"").append(pathname).append("\" 下的文件");
            } else {
                stringBuilder.append("要重命名的文件  \"").append(pathname).append("\" ");
            }
            stringBuilder.append("不存在");

            if (fileReplacePosition == FileReplacePosition.START) {
                stringBuilder.append("以 \"").append(replaceString).append("\" 开始的字符");
            } else if (fileReplacePosition == FileReplacePosition.END) {
                stringBuilder.append("以 \"").append(replaceString).append("\" 结束的字符");
            } else {
                stringBuilder.append("要替换的字符 \"").append(replaceString).append("\"");
            }

            System.out.println(stringBuilder);
        }
    }

    /**
     * @param newFile
     * @param newFiles
     */
    private static void addNewFile(File newFile, List<File> newFiles) {
        if (null != newFile) {
            newFiles.add(newFile);
        }
    }

    /**
     * @param pathname
     * @param replaceString
     * @param newString
     * @param fileReplacePosition
     * @param file
     */
    private static File fileRename(String pathname, String replaceString, String newString, FileReplacePosition fileReplacePosition, File file) {
        File newFile = null;
        String filename = file.getName();

        if (fileReplacePosition == FileReplacePosition.START && filename.startsWith(replaceString)) {
            newFile = fileRename(file, replaceString, newString);
        } else if (fileReplacePosition == FileReplacePosition.END && filename.endsWith(replaceString)) {
            newFile = fileRename(file, replaceString, newString);
        } else if (fileReplacePosition == FileReplacePosition.CONTAINS/* && filename.contains(replaceString) */) {
            newFile = fileRename(file, replaceString, newString);
        } else {
            return null;
        }

        return newFile;
    }

    /**
     * @param replaceString
     * @param file
     * @return
     */
    private static File fileRename(File file, String replaceString, String newString) {

        String[] replaceStrArr = replaceString.split("#");
        String[] newStrArr = newString.split("#");
        if (null == newString || newString.length() == 0) {
            int len = replaceStrArr.length;
            newStrArr = new String[len];
            for (int i = 0; i < len; i++) {
                newStrArr[i] = "";
            }
        }
        if (replaceStrArr.length != newStrArr.length) {
            String[] tempStrArr = new String[replaceStrArr.length];
            for (int i = 0, j = replaceStrArr.length; i < j; i++) {
                tempStrArr[i] = "";
            }
            for (int i = 0, j = newStrArr.length; i < j; j++) {
                tempStrArr[i] = newStrArr[i];
            }
            newStrArr = tempStrArr;
        }
        String filePath = file.getPath();
        int index = filePath.lastIndexOf("\\");
        String preffix = filePath.substring(0, index);
        String realFilePath = filePath.substring(index + 1);
        String newFilePath = filePath;
        // String newFilePath = "";
        File newFile = null;
        for (int i = 0, len = replaceStrArr.length; i < len; i++) {
            if (!newFilePath.contains(replaceStrArr[i]))
                continue;
            // 只重命名文件名
            if (index != 1) {
                // newFilePath = preffix.concat(File.separator).concat(realFilePath.replaceAll(replaceStrArr[i], newStrArr[i]));
                newFilePath = preffix.concat(File.separator).concat(realFilePath.replace(replaceStrArr[i], newStrArr[i]));
            } else {
                newFilePath = filePath.replaceAll(replaceStrArr[i], newStrArr[i]);
            }
        }
        if (null != newFilePath && !"".equals(newFilePath) && !newFilePath.equals(filePath)) {
            newFile = new File(newFilePath);
            file.renameTo(newFile);
            filePath = newFile.getPath();
        }

        return null == newFile ? file : newFile;
    }

    // 在指定位置插入指定长度字符
    public static void fileRenameByPosAndLen(String filePath, int pos, String replaceString) {
        fileRenameByPosAndLen(filePath, pos, 0, replaceString);
    }

    // 在指定位置按指定长度置空字符
    public static void fileRenameByPosAndLen(String filePath, int pos, int len) {
        fileRenameByPosAndLen(filePath, pos, len, "");
    }

    /**
     * 在指定位置按指定字符重命名其指定长度的字符
     *
     * @param filePath      文件路径
     * @param pos           开始位置，从1开始，为0时表示从头追加
     * @param len           重命名指定长度
     * @param replaceString 新字符串
     */
    public static void fileRenameByPosAndLen(String filePath, int pos, int len, String replaceString) {
        File file = new File(filePath);
        if (null == file || !file.exists()) {
            System.out.println("file not exists.");
            return;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                toFileRenameByPosAndLen(f, pos, len, replaceString);
            }
        } else {
            toFileRenameByPosAndLen(file, pos, len, replaceString);
        }

    }

    private static void toFileRenameByPosAndLen(File file, int pos, int len, String replaceString) {
        len = len < 0 ? 0 : len;
        pos = pos < 0 ? 0 : pos;
        replaceString = (null == replaceString || "".equals(replaceString.trim()) ? "" : replaceString);
        String filePath = file.getPath();
        int index = filePath.lastIndexOf("\\");
        String preffix = filePath.substring(0, index);
        String realFilePath = filePath.substring(index + 1);
        String newFileName;
        // pos--;

        if (len == 0) {
            if ("".equals(replaceString))
                return;
            // append string op
            int sufix = realFilePath.lastIndexOf(".");
            String realFileName = realFilePath.substring(0, sufix);
            String fileType = realFilePath.substring(sufix);
            if (pos == 0) {
                newFileName = replaceString.concat(realFilePath);
            } else if (pos == realFileName.length()) {
                newFileName = realFileName.concat(replaceString).concat(fileType);
            } else {
                // pos++;
                newFileName = realFilePath.substring(0, pos).concat(replaceString).concat(realFilePath.substring(pos));
            }
        } else {
            // replace string op
            if (pos == 0) {
                newFileName = replaceString.concat(realFilePath.substring(len));
            } else {
                // pos++;
                newFileName = realFilePath.substring(0, pos).concat(replaceString).concat(realFilePath.substring(pos + len));
            }
        }
        File newFile = new File(preffix.concat(File.separator).concat(newFileName));
        file.renameTo(newFile);
        // System.out.println("oldFile: " + filePath);
        // System.out.println("newFileName: " + newFileName);
        System.out.println(newFileName);
    }

    /**
     * 文件重命名的字符替换位置枚举
     *
     * @author Administrator
     */
    public enum FileReplacePosition {
        START, END, CONTAINS;
    }

    public static Properties loadPropertiesFile(String pathname) throws IOException {
        System.out.println("DB_PROPERTIES_FILE_PATH: " + pathname);
        Properties properties = new Properties();
        properties.load(FileUtil.class.getResourceAsStream(pathname));

        return properties;
    }

    public static String replaceFileNamePointToAbliqueLine(String filename) {
        if (StringUtil.isEmpty(filename)) {
            return filename;
        }

        return filename.replace(".", File.separator);
    }

    public static String replaceFileNameObliqueLineToPoint(String filename) {
        if (StringUtil.isEmpty(filename)) {
            return filename;
        }

        return filename.replace("/", ".").replace(File.separator, ".");
    }

    /**
     * 文件补零
     *
     * @param pathname        文件路径
     * @param zeroFillCount   填补多少个0
     * @param numPersistCount 保留多少位数字
     * @param isReplaceFirst  是否只替换首次出现的位置
     */
    public static void zeroFillToFileName(String pathname, int zeroFillCount, int numPersistCount, boolean isReplaceFirst) {
        numFillToFileName(pathname, 0, zeroFillCount, numPersistCount, isReplaceFirst);
    }

    /**
     * 文件补数
     *
     * @param pathname        文件路径
     * @param num             填充的数
     * @param numFillCount    填补多少个指定的数字
     * @param numPersistCount 保留多少位数字
     * @param isReplaceFirst  是否只替换首次出现的位置
     */
    public static void numFillToFileName(String pathname, int num, int numFillCount, int numPersistCount, boolean isReplaceFirst) {
        File file = new File(pathname);
        if (null == file || !file.exists()) {
            return;
        }

        numFillCount = numFillCount <= 0 ? 1 : numFillCount;
        String numStr = String.valueOf(num);
        String numFillStr = "";

        for (int i = 0; i < numFillCount; i++) {
            numFillStr += numStr;
        }

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                if (f.isFile()) {
                    numFilling(f, numStr, numFillStr, numFillCount, numPersistCount, isReplaceFirst);
                }
            }
        } else {
            numFilling(file, numStr, numFillStr, numFillCount, numPersistCount, isReplaceFirst);
        }
    }

    /**
     * 文件补零重命名操作
     *
     * @param file            要重命名的文件
     * @param zeroFillStr     要追加的0的字符串
     * @param zeroFillCount   要追加多少个0
     * @param numPersistCount 最终文件名里要保留的数值个数
     * @param isReplaceFirst  是否只替换首次数值的位置
     */
    private static void numFilling(File file, String numStr, String zeroFillStr, int zeroFillCount, int numPersistCount, boolean isReplaceFirst) {
        String parentPath = file.getParent();
        String filename = file.getName();

        if (isReplaceFirst) {
            filename = filename.replaceFirst("(\\d+)", zeroFillStr + "$1");
            filename = filename.replaceFirst(numStr + "*(\\d{" + numPersistCount + "})", "$1");
        } else {
            filename = filename.replaceAll("(\\d+)", zeroFillStr + "$1");
            filename = filename.replaceAll(numStr + "*(\\d{" + numPersistCount + "})", "$1");
        }

        String filepath = parentPath.concat(File.separator).concat(filename);
        File newFile = new File(filepath);
        boolean isFileRename = file.renameTo(newFile);
        if (isFileRename) {
            System.out.println(newFile.getName());
        }
    }

    /**
     * 文件名追加
     *
     * @param path
     * @param append
     * @param position 追加位置："main" - 开始位置，"end" - 结束位置，其它数值则代表相应索引
     */
    public static void appendName(String path, String append, String position) {
        File f = new File(path);
        if (null == f || !(f.exists() || f.canRead() || f.canWrite())) {
            System.out.println("return...");
            return;
        }
        int index = -1;
        if ("main".equals(position) || "0".equals(position)) {
            index = 0;
        } else if ("end".equals(position)) {
            index = -2;// 需重新计算
        } else {
            index = Integer.valueOf(position) - 1;
        }
        if (index == -1) {
            System.out.println("position 参数错误,,,");
            return;
        }
        File[] list = f.listFiles();
        List<File> newFiles = new ArrayList<>();
        for (File ff : list) {
            if (!ff.isFile() || ff.isHidden()) {
                // 不递归
                continue;
            }
            String filePath = ff.getPath();
            String newFilePath = "";
            // 只重命名文件名
            int pindex = filePath.lastIndexOf("\\");
            String preffix = filePath.substring(0, pindex);
            String realFilePath = filePath.substring(pindex + 1);
            if (index == 0) {
                newFilePath = append.concat(realFilePath);
            } else if (index == -2) {
                int suffixIndex = realFilePath.lastIndexOf(".");
                newFilePath = realFilePath.substring(0, suffixIndex).concat(append).concat(realFilePath.substring(suffixIndex));
            } else {
                newFilePath = realFilePath.substring(0, index).concat(append).concat(realFilePath.substring(index));
            }
            newFilePath = preffix.concat(File.separator).concat(newFilePath);
            File newFile = new File(newFilePath);
            ff.renameTo(newFile);
            newFiles.add(newFile);
        }

        System.out.println("all effict files:\n");
        for (File file : newFiles) {
            System.out.println(file.getName());
        }
    }

    @Test
    public void testRenameBaiduMusic() {
        FileUtil.renameBaiduMusic(null);
    }

    public static void renameBaiduMusic(String path) {
        String defPath = "D:\\KuGou\\Songs";
        path = (null == path || path.trim().length() == 0) ? defPath : path;
        File file = new File(path);
        if (null == file || !file.exists()) {
            System.out.println("file " + path + " not exists...");
            return;
        }
        if (!file.isDirectory()) {
            return;
        }
        File[] files = file.listFiles();
//        System.out.println("index\tname\tsuffix\tsinger");
        System.out.println("effect rename files: ");
        for (File f : files) {
            String parentPath = f.getParent();
            String fname = f.getName();
            int index = fname.lastIndexOf(".");
            String suffix = fname.substring(index);
            fname = fname.substring(0, index);
//            System.out.println(index + "\t" + fname + "\t" + suffix);

            index = fname.lastIndexOf("-");
            String singer = fname.substring(index + 1);
            fname = fname.substring(0, index);
//            System.out.println(index + "\t" + fname + "\t" + suffix + "\t" + singer);
//            System.out.println();

            fname = singer.concat(" - ").concat(fname).concat(suffix);
//            System.out.println("new file name:\n" + fname);
            fname = parentPath.concat(File.separator).concat(fname);
//            System.out.println("new file name(full path):\n" + fname);
//            System.out.println();

            f.renameTo(new File(fname));

            System.out.println(f.getAbsoluteFile());
//            System.out.println("new file f.getAbsoluteFile():\n" + f.getAbsoluteFile());
//            System.out.println();
        }
    }

    @Test
    public void testRenameDictionary() {
//        renameDictionary("D:\\Files\\b\\新建文件夹 (3)", null, "-111", 2, 2);
//        renameDictionary("D:\\Tutorial Videos\\Cloud,Cluster\\IT18", null, "BIGDATA-", 0, null);
//        renameDictionary("D:\\Tutorial Videos\\FrontEnd", "FrontEnd", "FrontEnd-", 0, null);
//        renameDictionary("D:\\Tutorial Videos\\Java", "", "Java-", 0, null);
        renameDictionary("D:\\Tutorial Videos\\SSH-SSM", "", "SSH-SSM-", 0, null);
    }

    /**
     * 重命名文件夹
     * <pre>
     * 思路：
     * replaceString,newString        皆为空   - 退出
     * replaceString 为空,newString    不为空
     *      1.len为空，据pos追加
     *      2.pos，len皆不为空          替换指定位置pos到指定长度len的字符为newString
     *
     * replaceString,newString        皆不为空 - 包含性替换
     * </pre>
     *
     * @param path
     * @param replaceString
     * @param newString
     * @param pos           开始的位置：0：从头追加；-1：末尾追加；其它从指定的位置开始
     * @param len           替换的长度
     */
    public void renameDictionary(String path, String replaceString, String newString, Integer pos, Integer len) {
        File dic = new File(path);
        if (!dic.exists()) {
            System.out.println("file not exists...");
            return;
        }
        if (StringUtil.isEmpty(replaceString) && StringUtil.isEmpty(newString)) {
            System.out.println("nothing to op...");
            return;
        }

        int newlen = (null == len ? 0 : len) + pos;
        for (File file : dic.listFiles()) {
            if (!file.isDirectory()) continue;
            String filePath = file.getPath();
            int index = filePath.lastIndexOf("\\");
            String preffix = filePath.substring(0, index);
            String realFilePath = filePath.substring(index + 1);
//            String newFilePath = filePath;
            String newFilePath = "";

            if (StringUtil.isEmpty(replaceString)) {
                if (pos == 0) {
                    newFilePath = newString.concat(realFilePath);
                } else if (pos == -1) {
                    newFilePath = realFilePath.concat(newString);
                } else {
                    newlen = newlen > realFilePath.length() ? realFilePath.length() : newlen;
                    newFilePath = realFilePath.substring(0, pos).concat(newString).concat(realFilePath.substring(newlen));
                }
            } else {
                newFilePath = realFilePath.replace(replaceString, newString);
            }

            newFilePath = preffix.concat(File.separator).concat(newFilePath);

            if (null != newFilePath && !"".equals(newFilePath) && !newFilePath.equals(filePath)) {
                File newFile = new File(newFilePath);
                file.renameTo(newFile);
                System.out.println(newFile.getPath());
//                System.out.println(filePath);
//                System.out.println(newFilePath + "\n");
            }
        }
    }

    public static void main(String[] args) throws IOException {
//        StringBuilder sb1 = new StringBuilder();
        // sb1.append("第一讲#第二讲#第三讲#第四讲#第五讲#第六讲#第七讲#第八讲#第九讲#第十讲#第十一讲#第十二讲#第十三讲#第十四讲#第十五讲");
        // sb1.append("第十六讲#第十七讲#第十八讲#第十九讲#第二十讲#第二十一讲#第二十二讲#第二十三讲#第二十四讲#第二十五讲#第二十六讲#第二十七讲#第二十八讲#第二十九讲#第三十讲");
        // sb1.append("第三十一讲#第三十二讲#第三十三讲#第三十四讲#第三十五讲#第三十六讲#第三十七讲#第三十八讲#第三十九讲#第四十讲#第四十一讲#第四十二讲#第四十三讲#第四十四讲#第四十五讲");

//		StringBuilder sb2 = new StringBuilder();
        // sb2.append("01#02#03#04#05#06#07#08#09#10#11#12#13#14#15#16#17#18#19#20#21#22#23#24#25#26#27#28#29#30#31#32#33#34#35#36#37#38#39#40#41#42#43#44#45");

        // for(int i=1; i<=12; i++){
        // renameFileBySpecificString("D:\\Tutorial Videos\\C,C++\\C语言也能干大事之C语言开发网站教程\\Day" + i, ".MP04", ".mp4",
        // FileReplacePosition.CONTAINS);
        // }

        renameFileBySpecificString("F:\\SSH-SSM\\SSH-SSM-SpringBoot-动力节点", "动力节点_SpringBoot视频教程_");

        // renameFileBySpecificString("D:\\KuGou\\Temp", "kgtemp", "mp3", FileReplacePosition.CONTAINS);

//         zeroFillToFileName("F:\\人工智能\\人工智能开发课程(最适合大学生学的课程)【尚学堂·百战程序员201804版】\\01_人工智能开发及远景介绍（预科）【尚学堂·百战程序员】", 1, 2, true);
        // fileRenameByPosAndLen("D:\\Tutorial Videos\\PHP\\5天PHP轻松入门视频教程2015版\\apache知识", 0, 0, "Apache.");
        // numFillToFileName("D:\\Tutorial Videos\\C,C++\\黑盾技术论坛菜鸟基础起飞C++125课", 2, 1, 4, true);

        // for(int i=1; i<= 13; i++){
        // zeroFillToFileName("D:\\Tutorial Videos\\C,C++\\C语言也能干大事之C语言开发网站教程\\Day"+i, 1, 2, true);
        // }

        // String s = "abc.abc";
        // int suffixIndex = s.lastIndexOf(".");
        // System.out.println(s.subString(0, suffixIndex).concat("hai").concat(s.subString(suffixIndex)));

//        appendName("D:\\[www.hd1tj.org]Jade.A.Step.Into.The.Past.Complete.HDTV.720p.2Audio.x264-HDSTV", "@HD一条街", "0");

//        File ff = new File("F:\\Golang\\GO语言20小时快速入门");
//        for (File tf : ff.listFiles()) {
////            String fileName = tf.getName();
//            String prefix = "微服务.P3.";
////            for (File f : tf.listFiles()) {
//                String newname = tf.getParent().concat(File.separator).concat(prefix).concat(tf.getName());
////                System.out.println(newname);
//                tf.renameTo(new File(newname));
////            }
//        }
//        for (int i = 1, j = 4; i <= j; i++) {
//            String str = "";
//            if (j >= 10 && i < 10) {
//                str = "0";
//            }
//            str = str + i;
////         String str = "0" + i;
//            appendName("F:\\Golang\\beego框架深入浅出视频\\day0" + i, "D" + str + ".", "0");
//        }
    }

    /**
     * @author Administrator
     */
    static class StringUtil {

        private static final String DEFAULT_SRC_CHARSET_NAME = "ISO-8859-1";
        private static final String DEFAULT_DEST_CHARSET_NAME = "UTF-8";

        /**
         * @param str
         * @return
         * @throws UnsupportedEncodingException
         */
        public static String encodingString(String str) throws UnsupportedEncodingException {
            return encodingString(str, DEFAULT_SRC_CHARSET_NAME, DEFAULT_DEST_CHARSET_NAME);
        }

        /**
         * @param str
         * @param srcCharsetName
         * @return
         * @throws UnsupportedEncodingException
         */
        public static String encodingString(String str, String srcCharsetName) throws UnsupportedEncodingException {
            return encodingString(str, srcCharsetName, DEFAULT_DEST_CHARSET_NAME);
        }

        /**
         * @param str
         * @param charsetName
         * @param isDestCharsetName
         * @return
         * @throws UnsupportedEncodingException
         */
        public static String encodingString(String str, String charsetName, boolean isDestCharsetName)
                throws UnsupportedEncodingException {
            if (isDestCharsetName) {
                return encodingString(str, DEFAULT_SRC_CHARSET_NAME, charsetName);
            } else {
                return encodingString(str, charsetName, DEFAULT_DEST_CHARSET_NAME);
            }

        }

        /**
         * @param str
         * @param srcCharsetName
         * @param destCharsetName
         * @return
         * @throws UnsupportedEncodingException
         */
        public static String encodingString(String str, String srcCharsetName, String destCharsetName)
                throws UnsupportedEncodingException {
            if (isEmpty(str)) {
                return str;
            }

            if (isEmpty(srcCharsetName)) {
                srcCharsetName = DEFAULT_SRC_CHARSET_NAME;
            } else {
                if (!Charset.isSupported(srcCharsetName)) {
                    throw new UnsupportedEncodingException("指定的原编码方式：" + srcCharsetName + " 不是合法的编码方式！");
                }
            }

            if (isEmpty(destCharsetName)) {
                destCharsetName = DEFAULT_DEST_CHARSET_NAME;
            } else {
                if (!Charset.isSupported(destCharsetName)) {
                    throw new UnsupportedEncodingException("指定的目标编码方式：" + destCharsetName + " 不是合法的编码方式！");
                }
            }

            return new String(str.getBytes(Charset.forName(srcCharsetName)), Charset.forName(destCharsetName));
        }

        /**
         * @param str
         * @return
         */
        public static boolean isNull(String str) {
            return null == str;
        }

        /**
         * @param str
         * @return
         */
        public static boolean isEmpty(String str) {
            return isEmpty(str, true);
        }

        /**
         * @param str
         * @param isTrim
         * @return
         */
        public static boolean isEmpty(String str, boolean isTrim) {
            return isNull(str) || (isTrim ? str.trim().isEmpty() : str.isEmpty());
        }

        /**
         * @param str
         * @return
         */
        public static boolean isNotEmpty(String str) {
            return !isEmpty(str);
        }

        /**
         * @param str
         * @param isTrim
         * @return
         */
        public static boolean isNotEmpty(String str, boolean isTrim) {
            return !isEmpty(str, isTrim);
        }
    }
}

