package god.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {
    private static XSSFSheet excelWSheet;
    private static XSSFWorkbook excelWBook;
    private static XSSFCell cell;
    private static XSSFRow row;

    //设定要操作Excel的文件路径和Excel文件中的Sheet名称
    //在读写Excel的时候,均需要调用此方法
    public static void setExcelFile(String path,String sheetName){
        FileInputStream excelFile;

        try {
            excelFile = new FileInputStream(path);//实例化Excel文件的FileInputStream对象
            excelWBook = new XSSFWorkbook(excelFile);//实例化Excel文件的XSSFWorkbook的对象
            excelWSheet = excelWBook.getSheet(sheetName);//实例化XSSFSheet对象，指定Excel文件中的Sheet名称，后续用于单元格的操作
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //读取Excel文件指定单元格的函数-此函数只支持拓展名为.xlsx的Excle文件
    public static String getCellData(int rowNum,int colnum) throws Exception{
        //通过函数参数指定单元格的行号和列号，获取指定的单元格对象
        cell = excelWSheet.getRow(rowNum).getCell(colnum);
        //如果单元格的内容为字符串类型，则使用getStringCellValue()方法获取单元格的内容
        //如果单元格的内容为数字类型，则使用getNumericCellValue()方法获取单元格的内容
        //注意getNumericCell方法返回值为double类型，转换字符串类型必须在cell.getNumericCellValue()前面加""，用于强制转换double类型
        //到String类型。不加""则会抛出double类型无法转换到String类型的异常
        String cellData = cell.getCellType()==XSSFCell.CELL_TYPE_STRING?cell.getStringCellValue()+
                "":String.valueOf(Math.round(cell.getNumericCellValue()));
        return cellData;
    }

    //在Excel文件的执行单元格中写入数据
    public static void setCellData(int rowNum,int colNum,String result) throws Exception{
        row = excelWSheet.getRow(rowNum);//获取Excel文件中的行对象
        cell = row.getCell(colNum,row.RETURN_BLANK_AS_NULL);//如果单元格为空，则返回null
        if(cell==null){
            cell = row.createCell(colNum);//当单元格对象是null的时候，则创建单元格
            cell.setCellValue(result);//创建单元格后可以调用单元格对象的setCellValue方法设定单元格的值
        }
        else {
            cell.setCellValue(result);//单元格中有内容，则可以直接调用单元格对象的setCellValue方法设定单元格的值
        }

        FileOutputStream fileOutputStream = new FileOutputStream(Constant.TESTCASE_DATA);//实例化写入Excel文件输出流对象
        excelWBook.write(fileOutputStream);//将内容写入Excel文件中
        fileOutputStream.flush();//调用flush方法强制刷新写入文件
        fileOutputStream.close();//关闭文件输出流对象
        excelWBook = new XSSFWorkbook(new FileInputStream(Constant.TESTCASE_DATA));

    }

    //从Excel文件获取测试数据的静态方法
    public static Object[][] getTestData(String excelFilePath,String sheetName) throws Exception{
        //根据参数传入的数据文件路径和文件名称，组合出Excel数据文件的绝对路径，声明一个File文件对象
        File file = new File(excelFilePath);
        FileInputStream inputStream = new FileInputStream(file);//创建fileinputstream对象用于读取excle文件
        Workbook workbook = null;//声明workbook对象
        String fileExtensionNmae = excelFilePath.substring(excelFilePath.indexOf("."));//获取文件名参数拓展名,判断是.xlsx文件还是.xls文件
        //判断何种格式然后用何种对象进行实例化
        if(fileExtensionNmae.equals(".xlsx")){
            workbook = new XSSFWorkbook(inputStream);
        }
        else if(fileExtensionNmae.equals(".xls")){
            workbook = new HSSFWorkbook(inputStream);
        }

        //通过sheetName参数，生成sheet对象
        Sheet sheet = workbook.getSheet(sheetName);

        //获取Excel数据文件Sheet1中数据的行数，getLastRowNum 方法获取数据的最后行号,getFirstRowNum方法获取数据的第一行行号
        //注意：Excel文件的行号和列号都是从0开始的
        int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
        List<Object[]> records = new ArrayList<Object[]>();//创建名为records的list对象来存储从Excel数据文件读取的数据

        //使用二个for循环遍历Excel数据文件的所有数据（除了第一行，第一行是数据列名称）
        for (int i = 1; i < rowCount+1; i++) {
            Row row = sheet.getRow(i);//获取行对象
            /*声明一个数组，用来存储Excel数据文件每行中的测试用例和数据，数组的大小用getLastCell-2来进行动态申明
            实现测试数据个数和数组大小一致，因为Excel数据文件中的测试数据行最后二列为判断是否执行用例以及写入测试结果的二列
            */
            String fields[] = new String[row.getLastCellNum()-1];

            //if用于判断数据行是否要参与测试用例的执行，Excel文件的倒数第二列为数据行的状态位，标识为y，则表示此数据行
            //将要被测试脚本执行，标识为非y的数据行均被认为不会参与测试脚本的执行，会被跳过
            if(row.getCell(row.getLastCellNum()-1).getStringCellValue().equals("y")){
                for (int j = 0; j < row.getLastCellNum()-1; j++) {
                    //判断excel单元格字段是数字还是字符串，字符串调用getStringValue()方法获取，数字则用getNumeValue()方法获取
                    fields[j] = (String)(row.getCell(j).getCellType()==XSSFCell.CELL_TYPE_STRING?row.getCell(j).getStringCellValue() :""+
                            row.getCell(j).getNumericCellValue());
                }
                //fields数据对象存储到records的list中
                records.add(fields);
            }
        }
        //定义函数返回值，即Object[][],将存储测试数据的list转换为一个Object的二维数组
        Object[][] results = new Object[records.size()][];
        for (int i = 0; i < records.size(); i++) {
            results[i] = records.get(i);
        }
        return results;
    }

    public static int getLastColumnNum(){
        //返回数据文件最后一列的列号，如果有12列，则结果返回11
        return  excelWSheet.getRow(0).getLastCellNum()-1;
    }
}


