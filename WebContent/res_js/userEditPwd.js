//1.获取表单组件对象
var userpwdObj = document.getElementById("userpwd");
var newpwdObj = document.getElementById("newpwd");
var confirmpwdObj = document.getElementById("confirmpwd");
var idObj = document.getElementById("id")

var userpwdMsg = document.getElementById("userpwdMsg");
var newpwdMsg = document.getElementById("newpwdMsg");
var confirmpwdMsg = document.getElementById("confirmpwdMsg");
var locaObj = window.location;// 获取当前url
var contextPath = locaObj.pathname.split("/");// 获取当前项目名称
// 原密码是否正确检测局部刷新 --jQuery ajax--
userpwdObj.addEventListener('blur', function() {
	$.post("/" + contextPath[1] + "/user", 
		{
			key:"userPasswordExist",
			userpwd:userpwdObj.value
		}, function(data, status) {
		// alert("数据: \n" + data + "\n状态: " + status);
		if (data == "exist") {
			// 是：获取span，添加内容：“原密码不正确，请重新输入！”
			// 得到span元素
			userpwdMsg.innerHTML = "原密码正确，请继续输入要修改的密码！";
		} else {
			userpwdMsg.innerHTML = "原密码不正确，请重新输入！";
		}
	});
});
// 2.表单组件事件
// 2.1用户原密码事件处理
userpwdObj.addEventListener('keyup', function() {
	checkPassword(userpwdObj, userpwdMsg);
});

// 2.2用户新密码事件处理
newpwdObj.addEventListener('keyup', function() {
	checkPassword(newpwdObj, newpwdMsg);
});

// 2.3用户确认密码事件处理
confirmpwdObj.addEventListener('keyup', function() {
	checkConfirm(newpwdObj, confirmpwdObj, confirmpwdMsg);
});

// 3.验证用户密码修改表单事件处理
function checkUserEditPwdForm() {
	var bUserPwd = checkPassword(userpwdObj, userpwdMsg);
	var bNewPwd = checkPassword(newpwdObj, newpwdMsg);
	var bConfirm = checkConfirm(newpwdObj, confirmpwdObj, confirmpwdMsg);

	return bUserPwd && bNewPwd && bConfirm;
}
// 3.2验证用户密码修改表单事件处理
// var btnUserEditPwdObj = document.getElementById('btnUserEditPwd');
// btnUserEditPwdObj.addEventListener('click', function() {

// var bUserPwd = checkPassword(userpwdObj, userpwdMsg);
// var bNewPwd = checkPassword(newpwdObj, newpwdMsg);
// var bConfirm = checkConfirm(newpwdObj, confirmpwdObj, confirmpwdMsg);

// if (bUserPwd && bNewPwd && bConfirm) {
// //若为ture, 执行submit
// document.getElementById('userEditPwdForm').submit();
// }
// });
