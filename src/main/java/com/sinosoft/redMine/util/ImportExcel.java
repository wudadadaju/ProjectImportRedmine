package com.sinosoft.redMine.util;

import java.io.File;
import java.util.List;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;

import com.sinosoft.redMine.dto.RedMineExcelDto;

public class ImportExcel {
	
	/**
     * redMine导入，excel解析
     * 只导入一页
     * @param
     * @return
     * @author wupengju@sinosoft.com.cn
     */
    public static List<RedMineExcelDto> importParamsToList(String path)  throws Exception {
        ImportParams params = new ImportParams();
        params.setTitleRows(0);     //表格标题行数,默认0
        params.setHeadRows(1);      //表头行数,默认1
        params.setNeedSave(false);       //是否需要保存上传的Excel
        params.setSheetNum(1);
        File f = new File(path);
        if(!f.exists()){
            return null;
        }
        System.out.println(f);
        System.out.println(params);
        List<RedMineExcelDto> list = ExcelImportUtil.importExcel(f, RedMineExcelDto.class, params);
        return list;
    }
	
	
	
	
}
