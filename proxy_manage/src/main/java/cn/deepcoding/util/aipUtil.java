package cn.deepcoding.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.codec.binary.Base64;
import java.util.HashMap;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.util.Base64Util;

import sun.misc.BASE64Decoder;

public class aipUtil {

	// 设置APPID/AK/SK
	public static String APP_ID;
	public static String API_KEY;
	public static String SECRET_KEY;
	public static String GROUP_ID;
	public static Double SCORE;
	static {

		try {
			InputStream in = WeixinUtil.class.getClassLoader().getResourceAsStream("const.properties");
			Properties prop = new Properties();
			prop.load(in);
			APP_ID = (String) prop.get("APP_ID");
			API_KEY = (String) prop.get("API_KEY");
			SECRET_KEY = (String) prop.get("SECRET_KEY");
			GROUP_ID = (String) prop.get("GROUP_ID");
			SCORE = Double.valueOf(prop.get("SCORE").toString()) ;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * * @param options - 可选参数对象，key: value都为string类型 options - options列表:
	 * user_info 用户资料，长度限制256B quality_control 图片质量控制 **NONE**: 不进行控制
	 * **LOW**:较低的质量要求 **NORMAL**: 一般的质量要求 **HIGH**: 较高的质量要求 **默认 NONE**
	 * liveness_control 活体检测控制 **NONE**: 不进行控制 **LOW**:较低的活体要求(高通过率 低攻击拒绝率)
	 * **NORMAL**: 一般的活体要求(平衡的攻击拒绝率, 通过率) **HIGH**: 较高的活体要求(高攻击拒绝率 低通过率)
	 * **默认NONE**
	 */
	public static void main(String[] args) {
		// 初始化一个AipFace
		AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
		// 传入可选参数调用接口
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("user_info", "测试账号");
		options.put("quality_control", "NONE");
		options.put("liveness_control", "NONE");
		String uid = "c_1";

		// 参数为本地图片路径
		String image = "D:/deepcoding/.metadata/.plugins/org.eclipse.wst.server.core/tmp2/wtpwebapps/"
				+ "/images/upload/1554809648409.png";
		System.out.println(image);
		
		JSONObject updateUser = client.updateUser(getImgStr(image), "BASE64", GROUP_ID, uid, options);
		//JSONObject addUser = client.search(getImgStr(image), "BASE64", groupId, options);
		String  msg = (String) updateUser.get("error_msg");
		System.out.println("返回信息"+msg);
		if(!"SUCCESS".equals(msg)){
			JSONObject addUser = client.addUser(getImgStr(image), "BASE64", GROUP_ID, uid, options);
			System.err.println(addUser.toString(2));
		}
		 
		
		System.out.println(updateUser.toString(2));

	}

	/**
	 *  * 将图片转换成Base64编码     
	 *  * @param imgFile 待处理图片     
	 *  * @return     
	 */
	public static String getImgStr(String imgFile) {
		// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理

		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new String(Base64.encodeBase64(data));
	}
	/**
	 * 功能描述：base64字符串转换成图片
	 *
	 * @since 2016/5/24
	 */
	public static boolean GenerateImage(String imgStr, String filePath, String fileName) {
	    try {
	        if (imgStr == null) {
	            return false;
	        }
	        BASE64Decoder decoder = new BASE64Decoder();
	        //Base64解码
	        byte[] b = decoder.decodeBuffer(imgStr);
	        //如果目录不存在，则创建
	        File file = new File(filePath);
	        if (!file.exists()) {
	            file.mkdirs();
	        }
	        //生成图片
	        OutputStream out = new FileOutputStream(filePath + fileName);
	        out.write(b);
	        out.flush();
	        out.close();
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}
	 /**
     * 删除文件，可以是文件或文件夹
     *
     * @param fileName
     *            要删除的文件名
     * @return 删除成功返回true，否则返回false
     */
    public static boolean delete(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("删除文件失败:" + fileName + "不存在！");
            return false;
        } else {
            if (file.isFile())
                return deleteFile(fileName);
            else
                return deleteDirectory(fileName);
        }
    }
    /**
     * 删除单个文件
     *
     * @param fileName
     *            要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir
     *            要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            System.out.println("删除目录失败：" + dir + "不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = aipUtil.deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = aipUtil.deleteDirectory(files[i]
                        .getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            System.out.println("删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            System.out.println("删除目录" + dir + "成功！");
            return true;
        } else {
            return false;
        }
    }
}
