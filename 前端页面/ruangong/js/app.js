/**
 * 演示程序当前的 “注册/登录” 等操作，是基于 “本地存储” 完成的
 * 当您要参考这个演示程序进行相关 app 的开发时，
 * 请注意将相关方法调整成 “基于服务端Service” 的实现。
 **/
(function($, owner) {
	/**
	 * 用户登录
	 **/
	owner.login = function(loginInfo, callback) {
		callback = callback || $.noop;
		loginInfo = loginInfo || {};
		loginInfo.account = loginInfo.account || '';
		loginInfo.password = loginInfo.password || '';
		if(loginInfo.account.length < 2) {
			return callback('账号最短为 2 个字符');
		}
		if(loginInfo.password.length < 6) {
			return callback('密码最短为 6 个字符');
		}	
		mui.ajax({
			url: 'http://192.168.219.55:8000/api/sign/login',
			data: loginInfo,
			dataType: 'json', //服务器返回json格式数据
			type: 'POST', //HTTP请求类型
			timeout: 60000, //超时时间设置为10秒；
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded'
			},
			success: function(result) {
				//mui.toast();
				//服务器返回响应，根据响应结果，分析是否登录成功；
				if(result.status > 0) {
					return callback('用户名或密码错误');
				} else {
					var users = JSON.parse(localStorage.getItem('$users') || '[]');
					loginInfo.stuname = result.data.stuname || '';
					users.push(loginInfo);
					localStorage.setItem('$users', JSON.stringify(users));
					return owner.createState(loginInfo.account, loginInfo.stuname, callback);
				}
			},
			error: function(xhr, type, errorThrown) {
				//异常处理；
				//console.log(type);
				mui.toast(errorThrown);
			}
		});

		//		mui.post('http://192.168.219.55:8000/api/sign/login', {
		//			account: loginInfo.account,
		//			password: loginInfo.password
		//		}, function(result) {
		//			console.log(result);
		//			服务器返回响应，根据响应结果，分析是否登录成功；
		//			if(result.status > 0) {
		//				return callback('用户名或密码错误');
		//			} else {
		//				loginInfo.stuname = result.data.stuname || '';
		//				users.push(loginInfo);
		//				localStorage.setItem('$users', JSON.stringify(users));
		//				var authed = users.some(function(user) {
		//					return loginInfo.account == user.account && loginInfo.password == user.password;
		//				});
		//		
		//				if(authed) {
		//					return owner.createState(loginInfo.account, loginInfo.stuname, callback);
		//				} else {
		//					return callback('用户名或密码错误');
		//				}
		//			}
		//		}, 'json');
	};

	owner.createState = function(num, name, callback) {
		var state = owner.getState();
		state.account = num;
		state.stuname = name;
		state.token = "yusi19980311";
		owner.setState(state);
		return callback();
	};

	/**
	 * 获取当前状态
	 **/
	owner.getState = function() {
		var stateText = localStorage.getItem('$state') || "{}";
		return JSON.parse(stateText);
	};

	/**
	 * 设置当前状态
	 **/
	owner.setState = function(state) {
		state = state || {};
		localStorage.setItem('$state', JSON.stringify(state));
	};

	/**
	 * 获取应用本地配置
	 **/
	owner.setSettings = function(settings) {
		settings = settings || {};
		localStorage.setItem('$settings', JSON.stringify(settings));
	}

	/**
	 * 设置应用本地配置
	 **/
	owner.getSettings = function() {
		var settingsText = localStorage.getItem('$settings') || "{}";
		return JSON.parse(settingsText);
	}

	/**
	 * 用户修改密码
	 **/
	owner.reg = function(regInfo, callback) {
		callback = callback || $.noop;
		regInfo = regInfo || {};
		regInfo.account = regInfo.account || '';
		regInfo.password = regInfo.password || '';
		regInfo.newpass = regInfo.newpass || '';
		if(regInfo.password.length < 6) {
			return callback('密码最短需要 6 个字符');
		}
		if(regInfo.newpass.length < 6) {
			return callback('密码最短为 6 个字符');
		}
		mui.ajax({
			url: 'http://192.168.219.55:8000/api/sign/change',
			data: regInfo,
			dataType: 'json', //服务器返回json格式数据
			type: 'POST', //HTTP请求类型
			timeout: 60000, //超时时间设置为10秒；
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded'
			},
			success: function(result) {
				//mui.toast();
				//服务器返回响应，根据响应结果，分析是否登录成功；
				if(result.status > 0) {
					return callback('原密码错误，修改失败');
				} else {
					localStorage.removeItem('$users');
					return callback();
				}
			},
			error: function(xhr, type, errorThrown) {
				//异常处理；
				//console.log(type);
				mui.toast(errorThrown);
			}
		});
	};

}(mui, window.app = {}));