//1.获取表单组件对象
var usernameObj = document.getElementById("username");
var userpwdObj = document.getElementById("userpwd");
var confirmpwdObj = document.getElementById("confirmpwd");
var userxmObj = document.getElementById("userxm");
var birthdayObj = document.getElementById("birthday");
var emailObj = document.getElementById("email");

var usernameMsg = document.getElementById("usernameMsg");
var userpwdMsg = document.getElementById("userpwdMsg");
var confirmpwdMsg = document.getElementById("confirmpwdMsg");
var userxmMsg = document.getElementById("userxmMsg");
var birthdayMsg = document.getElementById("birthdayMsg");
var emailMsg = document.getElementById("emailMsg");
var locaObj = window.location;//获取当前url
var contextPath = locaObj.pathname.split("/");//获取当前项目名称
//用户名重名检测局部刷新 --jQuery ajax--
usernameObj.addEventListener('blur', function() {
    $.post("/"+contextPath[1]+"/user",
        {
            key:"usernameExist",
            username:usernameObj.value
        },
        function(data,status){
            //alert("数据: \n" + data + "\n状态: " + status);
            if (data == "exist") {
                // 是：获取span，添加内容：“用户名已被注册”
                //得到span元素
                usernameMsg.innerHTML = "用户名已被注册！";
            } else {
                usernameMsg.innerHTML = "";
            }
        });
});
//2.表单组件事件处理
//2.1用户名事件处理
usernameObj.addEventListener('keyup', function() {
    checkUsername(usernameObj, usernameMsg);
});

//2.2用户密码事件处理
userpwdObj.addEventListener('keyup', function() {
    checkPassword(userpwdObj, userpwdMsg);
});

//2.3用户确认密码事件处理
confirmpwdObj.addEventListener('keyup', function() {
    checkConfirm(userpwdObj, confirmpwdObj, confirmpwdMsg);
});

//2.4用户真实姓名事件处理
userxmObj.addEventListener('keyup', function() {
    console.log(1);
    checkUserxm(userxmObj,userxmMsg);
});

//2.5用户出生日期事件处理
birthdayObj.addEventListener('blur', function() {
    console.log(2);
    checkBirthday(birthdayObj,birthdayMsg);
});

//2.6用户邮箱事件处理
emailObj.addEventListener('keyup', function() {
    checkEmail(emailObj, emailMsg);
});

//3.验证用户注册表单事件处理
//3.1表单submit事件
function checkUserRegForm() {

    var bUsername = checkUsername(usernameObj, usernameMsg);
    var bPassword = checkPassword(userpwdObj, userpwdMsg);
    var bConfirm = checkConfirm(userpwdObj, confirmpwdObj, confirmpwdMsg);

    var bUserxm = checkUserxm(userxmObj, userxmMsg);
    var bBirthday = checkBirthday(birthdayObj, birthdayMsg);
    var bEmail = checkEmail(emailObj, emailMsg);

    return bUsername && bPassword && bConfirm && bUserxm && bBirthday && bEmail;
}

//3.2按钮click实现表单提交事件
var btnUserRegObj = document.getElementById('btnUserReg');
btnUserRegObj.addEventListener('click', function() {

    var bUsername = checkUsername(usernameObj, usernameMsg);
    var bPassword = checkPassword(userpwdObj, userpwdMsg);
    var bConfirm = checkConfirm(userpwdObj, confirmpwdObj, confirmpwdMsg);

    var bUserxm = checkUserxm(userxmObj, userxmMsg);
    var bBirthday = checkBirthday(birthdayObj, birthdayMsg);
    var bEmail = checkEmail(emailObj, emailMsg);

    if (bUsername && bPassword && bConfirm && bUserxm && bBirthday && bEmail) {
        //若为ture, 执行submit
        document.getElementById('userRegForm').submit();
    }
});