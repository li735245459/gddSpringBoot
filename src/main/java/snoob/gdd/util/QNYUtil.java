package snoob.gdd.util;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.BatchStatus;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.web.multipart.MultipartFile;
import snoob.gdd.GlobalCustomException;
import snoob.gdd.enums.ResultEnum;

import java.io.UnsupportedEncodingException;

/**
 * 七牛云
 */
public class QNYUtil {
    private static final String accessKey = "whTEdjKpXHLpLh77WrC_tLybFNKsP6tJnWPhgqP-"; // 七牛云账号的access key
    private static final String secretKey = "2755x1fBt7_7q-ssvQEA280Oc6JbMHxRUQPyX0I6"; // 七牛云账号的secret key
    private static final String bucket = "gdd-img"; // 七牛云账号的存储空间名称
    private static final String domain = "http://pc1nqgunf.bkt.clouddn.com/"; // 七牛云账号的存储空间名称的域名
    private static Configuration cfg;

    static {
        cfg = new Configuration(Zone.zone0()); // Zone.zone0()为华东机房
    }

    /**
     * 以字节数组的形式上传文件
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static String upFileByBytes(MultipartFile file) throws Exception {
        UploadManager uploadManager = new UploadManager(cfg);
        String key = null; //默认不指定key以文件内容的hash值作为文件名,否则以key的值为文件名
        try {
            byte[] uploadBytes = file.getBytes();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response qny_response = uploadManager.put(uploadBytes, key, upToken);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(qny_response.bodyString(), DefaultPutRet.class);
                // putRet.key); // 文件名
                // putRet.hash);// 文件名hash值
                return domain + putRet.key;
            } catch (QiniuException ex) {
                throw new GlobalCustomException(ResultEnum.ERROR_QNY_UPFILE);
            }
        } catch (UnsupportedEncodingException ex) {
            throw new GlobalCustomException(ResultEnum.ERROR_QNY_UPFILE);
        }
    }

    /**
     * 删除文件
     *
     * @param key(文件名)
     */
    public static void deleteFile(String key) {
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            throw new GlobalCustomException(ResultEnum.ERROR_QNY_DELETEFILE);
        }
    }

    /**
     * 批量删除文件
     *
     * @param keys(文件名字符串数组)
     */
    public static void deleteFiles(String[] keys) {
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            //单次批量请求的文件数量不得超过1000
            BucketManager.BatchOperations batchOperations = new BucketManager.BatchOperations();
            batchOperations.addDeleteOp(bucket, keys);
            bucketManager.batch(batchOperations);
        } catch (QiniuException ex) {
            throw new GlobalCustomException(ResultEnum.ERROR_QNY_DELETEFILE);
        }
    }

}
