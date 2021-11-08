package Tools;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

import DataTypes.Goals;
import DataTypes.User;

public class RequestClass implements Serializable {

	public String request;
	public User user;
	public Goals goal;
	public String[] data;
	public File file;
	public String refName;
	public String refID;
	public String users;
	public ArrayList<File> goalFiles;
	public boolean hasFile=false;
	RequestClass(String _request, User _user) {
		this.request = _request;
		this.user = _user;

	}
	RequestClass(String _request, Goals _goal) {
		this.request = _request;
		this.goal = _goal;
	}
	RequestClass(String _request, Goals _goal,ArrayList<File> _goalfiles) {
		this.request = _request;
		this.goal = _goal;
		this.goalFiles=_goalfiles;
		this.hasFile=true;
	}
	RequestClass(String _request) {
		this.request = _request;
		
	}
	RequestClass(String _request, String[] _data) {
		this.request = _request;
		this.data = _data;
	}
	RequestClass(String _request, String[] _data, File _file) {
		this.request = _request;
		this.data = _data;
		this.file = _file;
	}
	RequestClass(String _request, String _users) {
		this.request = _request;
		this.users = _users;
	}
	RequestClass(String _request, String _refName,String _refID) {
		this.request = _request;
		this.refName = _refName;
		this.refID = _refID;
	}
}
