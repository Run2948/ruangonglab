using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Web.Http;
using Newtonsoft.Json;
using ruangong.Models;

namespace ruangong.Controllers
{
    public class SignController : BaseApiController
    {
        /// <summary>
        /// 登录
        /// </summary>
        /// <param name="loginModel"></param>
        /// <returns></returns>
        public ResultModel login([FromBody] LoginModel loginModel)
        {
            if(loginModel.account == null || loginModel.password == null)
                return ResError("获取参数异常！");
            var loginUser = db.student.SingleOrDefault(u => u.stunum == loginModel.account.Trim() && u.password == loginModel.password.Trim());
            return loginUser != null?ResSuccess(loginUser):ResError("用户名或密码错误！");
        }

        /// <summary>
        /// 签到
        /// </summary>
        /// <param name="Json"></param>
        /// <returns></returns>
        public ResultModel arrive([FromBody]dynamic Json)
        {
            string account = Json.account;
            if (account == null)
                return ResError("获取参数异常！");
            var model = db.student.SingleOrDefault(u => u.stunum == account);
            if (model == null)
                return ResError("该学号不存在！");
            model.sign = 1;
            model.signall += 1;
            return db.SaveChanges() > 0 ? ResSuccess("签到成功！") : ResError("系统异常！");
        }

        /// <summary>
        /// 请假
        /// </summary>
        /// <param name="Json"></param>
        /// <returns></returns>
        public ResultModel leave([FromBody]dynamic Json)
        {
            string account = Json.account;
            if (account == null)
                return ResError("获取参数异常！");
            var model = db.student.SingleOrDefault(u => u.stunum == account);
            if (model == null)
                return ResError("该学号不存在！");
            model.leave = 1;
            model.leaveall += 1;
            return db.SaveChanges() > 0 ? ResSuccess("签到成功！") : ResError("系统异常！");
        }

        /// <summary>
        /// 修改密码
        /// </summary>
        /// <param name="passwordModel"></param>
        /// <returns></returns>
        public ResultModel change([FromBody] PasswordModel passwordModel)
        {
            if (passwordModel.account == null || passwordModel.password == null || passwordModel.newpass == null)
                return ResError("获取参数异常！");
            var model = db.student.SingleOrDefault(u => u.stunum == passwordModel.account.Trim() && u.password == passwordModel.password.Trim());
            if (model == null)
                return ResError("原密码错误！");
            model.password = passwordModel.newpass.Trim();
            return db.SaveChanges() > 0 ? ResSuccess("密码修改成功！") : ResError("系统异常！");
        }

        /// <summary>
        /// 签到列表
        /// </summary>
        /// <returns></returns>
        public ResultModel arriveall()
        {
            var list = db.student.Where(u => u.sign == 1).ToList();
            var obj = new { count = list.Count, data = list };
            return ResSuccess(obj);
        }

        /// <summary>
        /// 请假列表
        /// </summary>
        /// <returns></returns>
        public ResultModel leaveall()
        {
            var list = db.student.Where(u => u.leave == 1).ToList();
            var obj = new { count = list.Count, data = list };
            return ResSuccess(obj);
        }


        /// <summary>
        /// 签到人数
        /// </summary>
        /// <returns></returns>
        public ResultModel arrivecount()
        {
            var count = db.student.Count(u => u.sign == 1);
            return ResSuccess(count);
        }

        /// <summary>
        /// 请假人数
        /// </summary>
        /// <returns></returns>
        public ResultModel leavecount()
        {
            var count = db.student.Count(u => u.leave == 1);
            return ResSuccess(count);
        }

        /// <summary>
        /// 是否签到
        /// </summary>
        /// <param name="Json"></param>
        /// <returns></returns>
        public ResultModel signorleave([FromBody]dynamic Json)
        {
            string account = Json.account;
            if (account == null)
                return ResError("获取参数异常！");
            var sign = db.student.Any(u => u.stunum == account && u.sign == 1);
            var leave = db.student.Any(u => u.stunum == account && u.leave == 1);
            return sign || leave ? ResSuccess("签到过了！") : ResError("没有签到！");
        }

        /// <summary>
        /// 管理员重置签到状态
        /// </summary>
        /// <returns></returns>
        public ResultModel adminsign()
        {
            var list = db.student.ToList();
            try
            {
                foreach (var model in list)
                {
                    model.sign = 0;
                    model.leave = 0;    
                }
                db.SaveChanges();
                return ResSuccess();
            }
            catch
            {
                return ResError();
            }
        }
    }
}
