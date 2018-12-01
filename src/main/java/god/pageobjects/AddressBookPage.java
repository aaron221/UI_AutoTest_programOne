//package god.pageObjects;
//
//import god.util.ObjectMap;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//
//public class AddressBookPage {
//    private WebElement element = null;
//    private ObjectMap objectMap = new ObjectMap("C:\\Users\\wenjun\\Desktop\\maven\\objectMap.properties");
//    private WebDriver driver;
//
//    public AddressBookPage(WebDriver driver){
//        this.driver = driver;
//    }
//
//    //获取新建联系人按钮
//    public WebElement createContactPersonButton() throws Exception{
//        element = driver.findElement(objectMap.getLocator("126mail.addresBbook.createContactPerson"));
//        return element;
//    }
//
//    //获取新建联系人界面的姓名输入框
//    public WebElement contactPersonName() throws Exception{
//        element = driver.findElement(objectMap.getLocator("126mail.addresBbook.createPersonName"));
//        return element;
//    }
//
//    //获取新建联系人界面中的电子邮件输入框
//    public WebElement contactPersonEmail() throws Exception{
//        element = driver.findElement(objectMap.getLocator("126mail.addresBbook.contactPersonEmail"));
//        return element;
//    }
//
//    //获取新建联系人界面中的手机号码输入框
//    public WebElement contactPersonMobile() throws Exception{
//        element = driver.findElement(objectMap.getLocator("126mail.addresBbook.contactPersonMobile"));
//        return element;
//    }
//
//
//    //获取新建联系人界面中保存信息的确定按钮
//    public WebElement saveButton() throws Exception{
//        element = driver.findElement(objectMap.getLocator("126mail.addresBbook.saveButton"));
//        return element;
//    }
//
//}
