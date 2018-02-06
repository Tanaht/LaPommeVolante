package service;

/**
 * Created by gbrossault on 06/02/18.
 */


/**
 * response format :
 *
 * {
 *     'type': string,
 *     'data': object
 * }
 *
 * where 'type' in ['mission', 'report', 'gethistory', 'history']
 *
 */

public class CommunicationServerHelper {

    /**
     * throwMissionOrder|getReport|getOperationsHistory|getMissionInfo
     *  -> set request params
     *  -> use executeRequest with these params to get the result
     *  -> use extract response to get needed information
     */

    public void throwMissionOrder(){

    }

    public void getReport(){

    }

    public void getOperationsHistory(){

    }

    public void getMissionsInfo(){

    }

    public void executeRequest(){
        //set http params, request, post with params given
    }

    public void extractResponse(){
        //get type and data
    }
}
