var regex = /^\d+$/;//校验是否为数值的正则表达式
	
function deleteUser(id, pageNo, pageSize, userName) {
	if (confirm("你确定要删除用户 " + userName + " ？")) {
		var uri="/Main?operate=del&id="+id+"&name="+userName+"&pageNo="+pageNo+"&pageSize="+pageSize;
		window.open(uri, "_self");
	}
}

function trMouseOver(obj) {
	var bgColor = obj.css("background-color");

	obj.children(":first").children("input[id='backBgColor']").val(bgColor);

	obj.css("background-color", "#666666");
}

function trMouseOut(obj) {
	obj.css("background-color", obj.children(":first").children(
			"input[id='backBgColor']").val());
}

function isEmpty(obj){
	var type=typeof obj;
	if(type=="string"){
		return null==obj || obj.trim().length()==0;
	}else if(type=="object"){
		return null==obj;
	}else if(type=="array"){
		return null==obj || obj.length==0;
	}else{
		return true;
	}
}

function iptPageNofun(){
	var pageNoIpt = $("#iptPageNo");
	var skipPageNoDefaultLabel = $("#skipPageNoDefaultLabel");
	var skipPageNoLabel = $("#skipPageNoLabel");
	var val = pageNoIpt.val();
	if(null==val || val.length==0){
		skipPageNoDefaultLabel.show();
		skipPageNoLabel.hide();
		return;
	}
	
	if(!regex.test(val)){
		skipPageNoDefaultLabel.show();
		skipPageNoLabel.hide();
	}else{
		skipPageNoDefaultLabel.hide();
		skipPageNoLabel.show();
	}
}

function skipPageNo(pageNo, pageSize){
	var pageSizeStr = $("#iptPageSize").val();
	pageSize = regex.test(pageSizeStr) ? pageSizeStr : pageSize;
	var url = "/Main?pageNo="+pageNo+"&pageSize="+pageSize;
	window.open(url, "_self");
}

function setPageSize(pageSize, pageNo){
	var pageNoStr = $("#skipPageNoLabel").val();
	pageNo = regex.test(pageNoStr) ? pageNoStr : pageNo;
	var url = "/Main?pageNo="+pageNo+"&pageSize="+pageSize;
	window.open(url, "_self");
}

function refreshPage(){
	var url = "/Main?isRefresh=true";
	window.open(url, "_self");
}

function toSpecificPage(pageNo, pageSize){
	var url = "/Main?pageNo="+pageNo+"&pageSize="+pageSize;
	window.open(url, "_self");
}

function manageUser(id){
	
	var url = "/DetailUser?userId="+id;
	//window.showModalDialog(url, window);
	var modalWinFeatures = "width=800, height=450, top=100, left=100," +
						   "directories=no,location=no,menubar=no,status=no,statusbar=no,topbar=no,titlbar=no,toolbar=no,resizable=no";
	
	/* channelmode=yes|no|1|0 是否使用剧院模式显示窗口。默认为 no。 
	directories=yes|no|1|0 是否添加目录按钮。默认为 yes。 
	fullscreen=yes|no|1|0 是否使用全屏模式显示浏览器。默认是 no。处于全屏模式的窗口必须同时处于剧院模式。 
	height=pixels 窗口文档显示区的高度。以像素计。 
	left=pixels 窗口的 x 坐标。以像素计。 
	location=yes|no|1|0 是否显示地址字段。默认是 yes。 
	menubar=yes|no|1|0 是否显示菜单栏。默认是 yes。 
	resizable=yes|no|1|0 窗口是否可调节尺寸。默认是 yes。 
	scrollbars=yes|no|1|0 是否显示滚动条。默认是 yes。 
	status=yes|no|1|0 是否添加状态栏。默认是 yes。 
	titlebar=yes|no|1|0 是否显示标题栏。默认是 yes。 
	toolbar=yes|no|1|0 是否显示浏览器的工具栏。默认是 yes。 
	top=pixels 窗口的 y 坐标。 
	width=pixels 窗口的文档显示区的宽度。以像素计。  */
	
	
	window.open(url, "_blank", modalWinFeatures);
}

function userLogout(usName){
	if(confirm("你确定要退出用户：" + usName + " ?")){
		window.location.href = "/iusermanager/login.jsp";
	}
}