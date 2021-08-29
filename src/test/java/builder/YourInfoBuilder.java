package builder;

import model.YourInfoModel;

/**
 * Created by Rakesh.
 */
public class YourInfoBuilder extends YourInfoModel {

	YourInfoModel infomodel = new YourInfoModel();

    public YourInfoBuilder(String[] infoDetails){
    	infomodel.setFirstName(infoDetails[0]);
    	infomodel.setLastName(infoDetails[1]);
    	infomodel.setPostalCode(infoDetails[2]);
    }

    public YourInfoModel getInfoDetails(){
        return infomodel;
    }
}
