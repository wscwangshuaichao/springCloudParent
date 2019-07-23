package springcloud.learn.wsc.servicehi.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
* @ClassName: ExportUtils.java
* @Description: 导出Excel
* @author: ChongLi
* @date: 2019/5/31 16:05
* @version V1.0
*/
@Slf4j
public class ExportUtils {
    /**
     * @Method 导出报表
     * @MethodName  exportForm
     * @Param titles 表头
     * @Param titleMap 表头名中英文映射
     * @Param result 结果
     * @Param fileFormat 文件类型
     * @Param tableName 表名
     */
    public static void exportForm(HttpServletRequest request, HttpServletResponse response, Map<String,String> titleMap,
                                  String[] titles, List<Map<String,Object>> result, String fileFormat, String tableName) {

        // 文件路径
        String currentPath = request.getSession().getServletContext().getRealPath("/");

        String fileName = System.currentTimeMillis() + fileFormat;

        // 生成工作区
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成片
        HSSFSheet sheet = workbook.createSheet(tableName);

        // 创建第一行
        HSSFRow row = sheet.createRow(0);
        // 设置表头
        for(int i=0; i<titles.length; i++){
            row.createCell(i).setCellValue(titles[i]);
        }

        for (int i = 0; i < result.size(); i++) {
            HSSFRow rows = sheet.createRow(i + 1);
            Map<String, Object> temp = result.get(i);

            for(int j=0; j < titles.length; j++){

                String title = titles[j];
                Object value = "";
                if(temp.containsKey(titleMap.get(title))){
                	value = temp.get(titleMap.get(title)) == null? "" : temp.get(titleMap.get(title));
                }
                rows.createCell(j).setCellValue(value.toString());
            }
        }
        ByteArrayOutputStream os = null ;
        try {

            if (request == null || response == null) {
                FileOutputStream exportXls = new FileOutputStream("E://工单信息表.xls");
                workbook.write(exportXls);
                exportXls.close();
            } else {
                os = new ByteArrayOutputStream();
                workbook.write(os);
                os.flush();
                response.reset();
                response.setContentType("application/x-excel");
                response.setCharacterEncoding("UTF-8");
                response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
                response.setHeader("Content-Length", String.valueOf(os.toByteArray().length));
                response.getOutputStream().write(os.toByteArray());
                response.getOutputStream().flush();

            }


            log.info("导出成功!");
        } catch (FileNotFoundException e) {
            log.error("导出失败!", e);
        } catch (IOException e) {
            log.error("导出失败!", e);
        }catch (Exception e) {
            log.error("导出失败!", e);
        }finally {
            if(os != null ) {
                try {
                    os.close();
                    os = null;
                    response.getOutputStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
