package com.github.bestheroz.standard.common.file;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.util.MyFileUtils;
import com.google.gson.JsonObject;

@Controller
public class FileUploadController {
    @RequestMapping(value = "/common/file/upload/fileUpload", method = RequestMethod.POST)
    @ResponseBody
    public JsonObject uploadFile(@RequestParam("file") final MultipartFile multipartFile, final String targetDirPath) throws CommonException {
        final JsonObject result = CommonException.EXCEPTION_SUCCESS_NORMAL.getJsonObject();
        result.addProperty("fileName", MyFileUtils.uploadFile(multipartFile, targetDirPath).getName());
        return result;
    }

    @RequestMapping(value = "/common/file/upload/uploadAllFiles", method = RequestMethod.POST)
    @ResponseBody
    public JsonObject uploadAllFiles(final MultipartHttpServletRequest mRequest, final String targetDirPath) throws CommonException {
        final JsonObject result = CommonException.EXCEPTION_SUCCESS_NORMAL.getJsonObject();
        final StringBuffer stringBuffer = new StringBuffer();
        for (final File file : MyFileUtils.uploadAllFiles(mRequest, targetDirPath)) {
            stringBuffer.append(file.getName()).append("\n");
        }
        result.addProperty("fileName", stringBuffer.toString());
        return result;
    }
}
