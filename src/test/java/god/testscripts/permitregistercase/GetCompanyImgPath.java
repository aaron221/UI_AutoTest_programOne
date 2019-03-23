package god.testscripts.permitregistercase;

import god.util.Constant;


public class GetCompanyImgPath {
    public static String getCompanyImgPath(String companyName){
        String companyImgpath = null;
        companyImgpath = Constant.COMPANY_IMAGE+companyName+".png";
        return companyImgpath;
    }

}
