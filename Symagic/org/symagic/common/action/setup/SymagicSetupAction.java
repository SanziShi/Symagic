package org.symagic.common.action.setup;

import java.io.File;
import java.io.FileWriter;

import org.apache.commons.io.FileUtils;
import org.symagic.admin.utilty.AdminUtility;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;

public class SymagicSetupAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8277925588992924746L;

	private String databaseHost;
	private String databaseName;
	private String databasePost;
	private String databaseUser;
	private String databasePassword;

	@Override
	public String execute() throws Exception {

		JSONObject object = new JSONObject();
		object.put("dbname", databaseName);
		object.put("username", databaseUser);
		object.put("password", databasePassword);
		object.put("server", databaseHost);
		object.put("port", databasePost);

		try {
			File jdbcConf = new File(FileUtils.getUserDirectoryPath()
					+ "/jdbc.json");
			FileWriter writer = new FileWriter(jdbcConf);
			writer.write(object.toString());
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			return INPUT;
		}

		return super.execute();
	}

	@Override
	public void validate() {

		if (AdminUtility.isEmpty(databaseHost))
			this.addFieldError("databaseHost", "");

		if (AdminUtility.isEmpty(databaseName))
			this.addFieldError("databaseName", "");

		if (AdminUtility.isEmpty(databasePost))
			this.addFieldError("databasePost", "");

		if (AdminUtility.isEmpty(databaseUser))
			this.addFieldError("databaseUser", "");
		if (AdminUtility.isEmpty(databasePassword))
			this.addFieldError("databasePassword", "");

		super.validate();
	}

	public String getDatabaseHost() {
		return databaseHost;
	}

	public void setDatabaseHost(String databaseHost) {
		this.databaseHost = databaseHost;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getDatabasePost() {
		return databasePost;
	}

	public void setDatabasePost(String databasePost) {
		this.databasePost = databasePost;
	}

	public String getDatabaseUser() {
		return databaseUser;
	}

	public void setDatabaseUser(String databaseUser) {
		this.databaseUser = databaseUser;
	}

	public String getDatabasePassword() {
		return databasePassword;
	}

	public void setDatabasePassword(String databasePassword) {
		this.databasePassword = databasePassword;
	}

}