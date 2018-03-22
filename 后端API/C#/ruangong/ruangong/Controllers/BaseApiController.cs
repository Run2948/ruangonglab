using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using ruangong.Models;

namespace ruangong.Controllers
{
    public class BaseApiController : ApiController
    {
        protected static readonly Student1Entities db = new Student1Entities();

        #region JSON数据响应的封装

        protected ResultModel ResSuccess()
        {
            return ResInfo(ResStatus.Yes, "请求成功！", null);
        }

        protected ResultModel ResSuccess(string msg)
        {
            return ResInfo(ResStatus.Yes, msg, null);
        }

        protected ResultModel ResSuccess(object data)
        {
            return ResInfo(ResStatus.Yes, "请求成功！", data);
        }

        protected ResultModel ResError()
        {
            return ResInfo(ResStatus.No, "请求失败！", null);
        }

        protected ResultModel ResError(string msg)
        {
            return ResInfo(ResStatus.No, msg, null);
        }

        protected ResultModel ResInfo(ResStatus status, string msg, object data)
        {
            return new ResultModel()
            {
                status = (int)status,
                msg = msg,
                data = data
            };
        } 
        #endregion
    }
}
