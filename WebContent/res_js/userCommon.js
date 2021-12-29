//1.trim方法：去除字符串首尾空格
function trim(str) {
    return str.replace(/(^\s*)|(\s*$)/g, "");
}

//2.checkUsername方法：验证用户名
function checkUsername(usernameObj, usernameMsg) {
    var regex = /^[a-zA-Z_]\w{0,9}$/; // 正则表达式：字母数字下划线, 不能是数字开头
    var value = trim(usernameObj.value); // 获取usernameObj中的文本
    var msg = ""; // 最后的提示消息, 默认为空

    if (!value) { // 如果用户名没填, 不论null或者""都是false，否则填了就是一个字符串可以当作true
        msg = "请输入用户名;"; // 改变提示消息
    } else if (value.length < 6 || value.length > 20) {
        msg = "用户名的长度必须在6-20之间;"; // 改变提示消息
    } else if (!regex.test(value)) { // 如果用户名不能匹配正则表达式规则
        msg = "用户名不合法;"; // 改变提示消息
    }
    usernameMsg.innerHTML = msg; // 将提示消息放入SPAN标签
    //usernameObj.parentNode.parentNode.style.color = msg == "" ? "black" : "red";  // 根据消息结果改变tr的颜色
    return msg == ""; // 如果提示消息为空则代表验证通过用户名符合规则, 返回true
}

//3.验证密码
function checkPassword(userpwdObj, userpwdMsg) {
    var value = trim(userpwdObj.value);
    var msg = "";

    if (!value) {
        msg = "请输入用户密码;";
    } else if (value.length < 6 || value.length > 16) {
        msg = "密码的长度必须在6-16之间;";
    }
    userpwdMsg.innerHTML = msg;
    return msg == "";
}

//4.验证确认密码
function checkConfirm(userpwdObj, confirmpwdObj, confirmpwdMsg) {
    var passwordValue = trim(userpwdObj.value);
    var confirmValue = trim(confirmpwdObj.value);
    var msg = "";

    if (!confirmValue) {
        msg = "请输入确认密码;";
    } else if (passwordValue != confirmValue) {
        msg = "密码与确认密码不匹配;";
    }
    confirmpwdMsg.innerHTML = msg;
    return msg == "";
}

//5.验证用户姓名
function checkUserxm(userxmObj, userxmMsg) {
    var userxmValue = trim(userxmObj.value);
    var msg = "";

    if (!userxmValue) {
        msg = "请输入用户真实姓名;";
    }
    userxmMsg.innerHTML = msg;
    return msg == "";
}

//6.验证用户出生日期
function checkBirthday(birthdayObj, birthdayMsg) {
    var birthdayValue = trim(birthdayObj.value);
    var msg = "";

    if (!birthdayValue) {
        msg = "请输入用户出生日期;";
    }
    birthdayMsg.innerHTML = msg;
    return msg == "";
}

//7.验证用户邮箱
function checkEmail(emailObj, emailMsg) {
    var regex = /^[\w-]+@([\w-]+\.)+[a-zA-Z]{2,4}$/;
    var value = trim(emailObj.value);
    var msg = "";

    if (!value) {
        msg = "请输入用户邮箱;";
    } else if (!regex.test(value)) {
        msg = "邮箱格式错误;";
    }
    emailMsg.innerHTML = msg;
    return msg == "";
}