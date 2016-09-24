package yodlee.api.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import yodlee.api.io.HTTP;
import yodlee.api.util.Console;
import yodlee.ysl.api.beans.Accounts;
import yodlee.ysl.api.beans.LoginForm;
import yodlee.ysl.api.beans.Providers;
import yodlee.ysl.api.beans.RefreshStatus;
import yodlee.ysl.api.parsers.GSONParser;



public class TestYSLCL {

	private static final String fqcn = TestYSLCL.class.getName();

	public static final String localURLVer1 = "https://developer.api.yodlee.com/ysl/restserver/v1/";
	

	public static String HOST_URI =  	"https://rest.developer.yodlee.com/services/srest/restserver/";
		
	public static Map<String,String> loginTokens = new HashMap<String,String>();
	public static final String COB_LOGIN_URL =  "cobrand/login";
	public static final String USER_LOGIN_URL = "user/login";
	public static final String ADD_SITE_ACC = "providers/16441/";
	
	private String paramNameCobrandLogin = "cobrandLogin";
	private String paramNameCobrandPassword = "cobrandPassword";
	

	//User login API parameters
	private String paramNameUserLogin = "loginName";
	private String paramNameUserPassword = "password";
	
	private String paramNameCobSessionToken = "cobSessionToken";
	private String paramNameUserSessionToken = "userSessionToken";
	
	private static String SUBMIT_APPLICATION = "v1.0/jsonsdk/CreditLendingManagement/submitApplication";
	private static String RETRIEVE_APPLICATIONS = "v1.0/jsonsdk/CreditLendingManagement/getApplications";
	private static String DOWNLOAD_APPLICATION = "v1.0/jsonsdk/CreditLendingManagement/downloadCreditApplication";
	private static String DOWNLOAD_STATEMENT = "v1.0/jsonsdk/CreditLendingManagement/downloadStatement";
	
	private String paramAppId	 = "applicationId";
	private String paramStatementRefId = "statementRefId";

	public String doCoBrandLogin(String coBrandUserName, String coBrandPassword) throws IOException
	{
		DefaultHttpClient httpclient = new DefaultHttpClient();

		String url = localURLVer1 + COB_LOGIN_URL;
		System.out.println("Validating Cobrand by Connecting to URL " + url);
		String cobrandSessionToken = null;
		try
		{
			HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());
			PostMethod pm = new PostMethod(url);
			pm.addParameter(paramNameCobrandLogin, coBrandUserName);
			pm.addParameter(paramNameCobrandPassword, coBrandPassword);
			HttpClient hc = new HttpClient();
			hc.executeMethod(pm);
			System.out.println(pm.getResponseBodyAsString());
			String source = pm.getResponseBodyAsString();

			JSONObject jsonObject = new JSONObject(source);
			JSONObject cobConvCreds = jsonObject.getJSONObject("session");
			cobrandSessionToken = (String) cobConvCreds.get("cobSession");
			loginTokens.put("cobSession", cobrandSessionToken);

//			System.out.println("Cobrand Session " + cobrandSessionToken);
		}
	
		catch (Exception e) {
		e.printStackTrace();
		} 
		finally {
		httpclient.getConnectionManager().shutdown();
	}
	return cobrandSessionToken;
}

	public String doUserLogin(String cobrandSessionToken, String usernameValue,	String passwordValue)
	{
		String userSessionToken = null;
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String url = localURLVer1 + USER_LOGIN_URL;
		try {
			HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());
			PostMethod pm = new PostMethod(url);
			pm.addParameter(paramNameUserLogin, usernameValue);
			pm.addParameter(paramNameUserPassword, passwordValue);
			pm.addRequestHeader("authorization", "cobSession = "+cobrandSessionToken);

			HttpClient hc = new HttpClient();
			hc.executeMethod(pm);

			String source = pm.getResponseBodyAsString();
			JSONObject jsonObject = new JSONObject(source);
			JSONObject userContext = jsonObject.getJSONObject("user");
			JSONObject userConvCreds = userContext.getJSONObject("session");
			userSessionToken = (String) userConvCreds.get("userSession");
			loginTokens.put("userSession", userSessionToken);

			System.out.println(pm.getResponseBodyAsString());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		return userSessionToken;
}

	public static Providers getProviderLoginForm(String providerId) throws IOException,	URISyntaxException {
			String mn = "getProviderLoginForm(providerId " + providerId + " )";
			System.out.println(fqcn + " :: " + mn);
			String getSiteURL = localURLVer1 + "providers/" + providerId;
			String jsonResponse = HTTP.doGet(getSiteURL, loginTokens);
			Providers providers = (Providers) GSONParser.handleJson(
					jsonResponse, yodlee.ysl.api.beans.Providers.class);
			System.out.println(providers.toString());
			return providers;
}
	
	public static RefreshStatus addNonMFA_Account(Providers providers) throws IOException, URISyntaxException
	{
		
		Console con = new Console();
		/** Use this block to add Non - MFA account: 
		  	a) Non-MFA means a non multi factored authentication method where a simple userName/password suffices. 
		 	Use this method to input Provider (End Site -> credentials e.g. the Bank Of America UserName & Password goes in the next set of methods) **/
		if (con != null)
		{
			System.out.println(" Add addNonMFA_Account:  ");
			String userName = con.readLine("Enter your provider userName : ");
			// For internal Yodlee Dag tool use this hardcoded user Name = "DBmet1.site16441.1"
			providers.getProvider()[0].getLoginForm().getRow()[0].getField()[0].setValue(userName);
		}
		if (con != null)
		{
			String password = con.readLine("Enter provider password : ");
			// For internal Yodlee Dag tool use this hardcoded password = "site16441.1"
			providers.getProvider()[0].getLoginForm().getRow()[1].getField()[0].setValue(password);	
					
		}
			
		RefreshStatus refreshStatus = addProviderAccount(providers);
		while(!refreshStatus.getRefreshInfo().getRefreshStatus().equals("REFRESH_COMPLETED")){
			System.out.println("current add status: "+refreshStatus.getRefreshInfo().getRefreshStatus());
			refreshStatus = getRefreshStatus(refreshStatus.getProviderAccountId());
		}
		System.out.println("Account successfully added");
		return refreshStatus;
	}
	
	public static RefreshStatus getRefreshStatus(String providerAccountId)throws IOException, URISyntaxException {
		
		String mn = "getRefreshStatus( " + providerAccountId.toString() + " )";
		System.out.println(fqcn + " :: " + mn);
		String getRefreshStatusURL = localURLVer1 + "refresh/"
				+ providerAccountId.toString();
		String jsonResponse =  HTTP.doGet(getRefreshStatusURL, loginTokens);
		/*RefreshStatuss refreshStatus = (RefreshStatuss) GSONParser.handleJson(
				jsonResponse, yodlee.ysl.api.beans.RefreshStatuss.class);
		System.out.println(refreshStatus.toString());
		return refreshStatus.getProviderAccount();*/
		RefreshStatus refreshStatus = (RefreshStatus) GSONParser.handleJson(
				jsonResponse, yodlee.ysl.api.beans.RefreshStatus.class);
		System.out.println(refreshStatus.toString());
		return refreshStatus;
}

	
public static RefreshStatus addProviderAccount(Providers providers)	throws IOException, URISyntaxException {
			String mn = "addProviderAccount( " + providers.getProvider()[0].toString();
			System.out.println(fqcn + " :: " + mn);
			Gson gson = new GsonBuilder().disableHtmlEscaping().create();
			String providerJson = "{\"provider\":["+ gson.toJson(providers.getProvider()[0])+"]}";
			providerJson=providerJson.replaceAll("\\s+","");
			System.out.println(providerJson);
			String addSiteURL = localURLVer1 + "providers/" + providers.getProvider()[0].getId();
			String jsonResponse = HTTP.doPostUser(addSiteURL, loginTokens,	providerJson,false);
		/*RefreshStatuss refreshStatus = (RefreshStatuss) GSONParser.handleJson(
				jsonResponse, yodlee.ysl.api.beans.RefreshStatuss.class);
		System.out.println(refreshStatus.toString());
		return refreshStatus.getProviderAccount();*/
		RefreshStatus refreshStatus = (RefreshStatus) GSONParser.handleJson(jsonResponse, yodlee.ysl.api.beans.RefreshStatus.class);
		System.out.println(refreshStatus.toString());
		return refreshStatus;
}

public static RefreshStatus addMFA_Account(Providers providers) throws IOException, URISyntaxException, InterruptedException
{
	
	Console con = new Console();

	// Use this block to add an MFA account: 
	// a) MFA means a non multi factored authentication method where the site provides a challenge Q&A or Captcha to validate the request.
	// Use this method to input Provider (End Site -> credentials e.g. the Bank Of America UserName & Password goes in the next set of methods) 
	if (con != null)
	{
		System.out.println(" Add MFA_Account:  ");
		String userName = con.readLine("Enter your provider userName : ");
		// For internal Yodlee Dag tool use this hardcoded user Name = "DBmet1.site16441.1"
		providers.getProvider()[0].getLoginForm().getRow()[0].getField()[0].setValue(userName);
	}
	if (con != null)
	{
		String password = con.readLine("Enter provider password : ");
		// For internal Yodlee Dag tool use this hardcoded password = "site16441.1"
		providers.getProvider()[0].getLoginForm().getRow()[1].getField()[0].setValue(password);	
				
	}
	
	RefreshStatus refreshStatus = addProviderAccount(providers);
	while(refreshStatus.getLoginForm()==null)
	{
		refreshStatus = getRefreshStatus(refreshStatus.getProviderAccountId());
	}
	refreshStatus.getLoginForm().getRow()[0].getField()[0].setValue("123456");
	doChallenge(refreshStatus.getLoginForm(), refreshStatus.getProviderAccountId());
	refreshStatus = getRefreshStatus(refreshStatus.getProviderAccountId());
	while(refreshStatus.getLoginForm()==null)
	{
		refreshStatus = getRefreshStatus(refreshStatus.getProviderAccountId());
	}
	refreshStatus.getLoginForm().getRow()[0].getField()[0].setValue("Texas");
	refreshStatus.getLoginForm().getRow()[1].getField()[0].setValue("w3schools");
	doChallenge(refreshStatus.getLoginForm(), refreshStatus.getProviderAccountId());
	
//	refreshStatus = AddProviderAccount.addProviderAccount(providers);
	while(!refreshStatus.getRefreshInfo().getRefreshStatus().equals("REFRESH_COMPLETED")){
		System.out.println("current add status: "+refreshStatus.getRefreshInfo().getRefreshStatus());
		refreshStatus = getRefreshStatus(refreshStatus.getProviderAccountId());
	}
	System.out.println("Account successfully added");
	return refreshStatus;
	// Fetch the accounts if you need !
	
	
}

public static void doChallenge(LoginForm loginForm, String providerAccountId)throws IOException, URISyntaxException {
	String mn = "doChallenge( " + loginForm.toString() + " providerAccountId = " + providerAccountId;
	System.out.println(fqcn + " :: " + mn);
	Gson gson = new GsonBuilder().disableHtmlEscaping()
	.create();
	String providerJson = "{\"loginForm\":"+ gson.toJson(loginForm)+"}";
	System.out.println(providerJson);
	String addSiteURL = localURLVer1 + "providers/"+providerAccountId ;
	HTTP.doPut(addSiteURL,providerJson, loginTokens);
}

public static Accounts getAccounts() throws IOException,URISyntaxException {
		String mn = "getAccounts()";
		System.out.println(fqcn + " :: " + mn);
		String accountSummaryURL = localURLVer1 + "accounts/";
		String jsonResponse = HTTP.doGet(accountSummaryURL,loginTokens);
		System.out.println(jsonResponse);
		Accounts accounts =(Accounts) GSONParser.handleJson(
		jsonResponse, yodlee.ysl.api.beans.Accounts.class);
		return accounts;
}

//CL Submit Application
public String clSubmitApplication(String cobrandSessionToken,
		String userSessionToken, String itemAccountId)
//		,
//		String creditorName, String creditorTelNo, String creditorAddress1,
//		String creditorCity, String creditorState, String creditorZipCode) 
		{
	// String userSessionToken=null;
	DefaultHttpClient httpclient = new DefaultHttpClient();

	String url = HOST_URI + SUBMIT_APPLICATION;
	try {
		HttpsURLConnection
				.setDefaultHostnameVerifier(new NullHostnameVerifier());

		PostMethod pm = new PostMethod(url);


		
		pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
		pm.addParameter(paramNameUserSessionToken, userSessionToken);
		pm.addParameter("creditApplication.linkedAccounts[0]",itemAccountId);
//		pm.addParameter("creditApplication.creditorInfo.name", creditorName);
//		pm.addParameter("creditApplication.creditorInfo.telNo", creditorTelNo);
//		pm.addParameter("creditApplication.creditorInfo.address.address1", creditorAddress1);
//		pm.addParameter("creditApplication.creditorInfo.address.city", creditorCity);
//		pm.addParameter("creditApplication.creditorInfo.address.state.abbreviation", creditorState);
//		pm.addParameter("creditApplication.creditorInfo.address.zipCode1", creditorZipCode);
		
//		pm.addParameter("creditApplication.applicationAttributes[0]", "tncVersion~1.0");
//		pm.addParameter("creditApplication.applicationAttributes[1]", "tncAccepted~Y");
//		pm.addParameter("creditApplication.creditorInfo.address.address2", "Suite 2123");
//		pm.addParameter("creditApplication.creditorInfo.address.country.countryCode", "US");
//		pm.addParameter("creditApplication.creditorInfo.email", "test@abcinc.com");

		HttpClient hc = new HttpClient();
		hc.executeMethod(pm);


		System.out.println(pm.getResponseBodyAsString());

	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		httpclient.getConnectionManager().shutdown();
	}

	return null;
}

//CL Retrieve Applications
public String clGetApplications(String cobrandSessionToken,
		String userSessionToken) {
	// String userSessionToken=null;
	DefaultHttpClient httpclient = new DefaultHttpClient();

	String url = HOST_URI + RETRIEVE_APPLICATIONS;
	try {
		HttpsURLConnection
				.setDefaultHostnameVerifier(new NullHostnameVerifier());

		PostMethod pm = new PostMethod(url);
		pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
		pm.addParameter(paramNameUserSessionToken, userSessionToken);


		HttpClient hc = new HttpClient();
		hc.executeMethod(pm);


		System.out.println(pm.getResponseBodyAsString());

	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		httpclient.getConnectionManager().shutdown();
	}

	return null;
}

//CL Download Application
public String clDownloadApplication(String cobrandSessionToken,
		String userSessionToken, String appId) {
	// String userSessionToken=null;
	DefaultHttpClient httpclient = new DefaultHttpClient();

	String url = HOST_URI + DOWNLOAD_APPLICATION;
	try {
		HttpsURLConnection
				.setDefaultHostnameVerifier(new NullHostnameVerifier());

		PostMethod pm = new PostMethod(url);
		pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
		pm.addParameter(paramNameUserSessionToken, userSessionToken);
		pm.addParameter(paramAppId, appId);


		HttpClient hc = new HttpClient();
		hc.executeMethod(pm);


		System.out.println(pm.getResponseBodyAsString());
		
//		String jsresponse = pm.getResponseBodyAsString();
//		
//		String jsonFormattedString1 = jsresponse.replace("\\\"", "\"");
//		System.out.println(jsonFormattedString1);
		
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		httpclient.getConnectionManager().shutdown();
	}

	return null;
}

public static void loopOption(){
				
	System.out.println(" \n Available Options");

				System.out.println("\n 1 Add Non MFA Account  " +
				"\n 2 Add MFA Account " +
				"\n 3 Get Accounts " +
				"\n 4 Submit Credit Lendin Application " +
				"\n 5 Get All Application " +
				"\n 6 Download Credit Appliacation " +
				"\n 0 To Exit  "	);

}
public static int readInt() {

	String readStr = readStr();

	try {
		return new Integer(readStr).intValue();
	} catch (NumberFormatException nfEx) {
		// throw new RuntimeException ("Invalid entry: " + readStr + ". You
		// must enter a number.");
		System.out.println("Invalid entry: " + readStr
				+ ". You must enter a number.");
		return -1;
	}
}
public static String readStr() {
	
	BufferedReader br = new BufferedReader (new InputStreamReader(System.in));

	String readStr = null;

	try {
		do {
			readStr = br.readLine();
			if (readStr != null) {
				/*readStr = readStr.substring(0,
						readStr.indexOf('#') == -1 ? readStr.length()
								: readStr.indexOf('#'));*/
			}
			readStr = readStr.trim();
		} while (readStr == null || (readStr.equalsIgnoreCase("")));
	} catch (IOException ioEx) {
		ioEx.printStackTrace();
		throw new RuntimeException("Error reading line!");
	}

	return readStr;

}

	public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException, JSONException {

		TestYSLCL test = new TestYSLCL();
		Console con = new Console();

		String cobrandLoginValue =  con.readLine("Enter your coBrandUserName: ");
        String cobrandPasswordValue = con.readLine("Enter " + cobrandLoginValue + "'s password: ");
        String usernameValue = con.readLine("Enter your userName: ");
        String passwordValue = con.readLine("Enter " + usernameValue + "'s password: ");
        
		String cobrandSessionToken = test.doCoBrandLogin(cobrandLoginValue, cobrandPasswordValue);
		System.out.println(cobrandSessionToken);
		
		System.out.println("\n\n\n\n loginUser ");
		String userSessionToken = test.doUserLogin(cobrandSessionToken, usernameValue, passwordValue);
		System.out.println(userSessionToken);
		RefreshStatus refreshStatus = null;

		Providers providers = null;
		boolean loop = true;
		while(loop)
		{
			loopOption();
			System.out.print("Choice: ");
			int choice = readInt();
			try
			{
				switch(choice)
				{
				case 1: 
					if (con != null) {
						String providerId = con.readLine("Enter the Provider Id : ");
						providers = getProviderLoginForm(providerId);
					}
						refreshStatus = addNonMFA_Account(providers);
						break;
				case 2:
					if (con != null) {
						String providerId = con.readLine("Enter the Provider Id : ");
						providers = getProviderLoginForm(providerId);
					}
						refreshStatus = addMFA_Account(providers);
						break;
				case 3:
							getAccounts();
							break;
				case 4:
							test.clSubmitApplication(cobrandSessionToken, userSessionToken, 10204544+"");
							break;
				case 5:	
							test.clGetApplications(cobrandSessionToken, userSessionToken);
							break;
				case 6:
							test.clDownloadApplication(cobrandSessionToken, userSessionToken, "CL1001035200000005");
							break;
				case 0:	
							loop=false;
							break;
				default:
							System.out.println("not a valid option");
				}	
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	private static class NullHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}


}
