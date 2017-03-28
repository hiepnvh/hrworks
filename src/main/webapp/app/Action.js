Ext.define(App.path('Action'), {
    alternateClassName: 'App.Action',
    statics: {
      ResponseCode: {
            OK: 0
      },
     AjaxRequest: function(url ,params, callback, scope) {
        Ext.Ajax.request({
            url: url,
            actionMethods : {
					create : 'POST',
					read : 'POST',
					update : 'POST',
					destroy : 'POST'
				},
            params: params,
            timeout: 300000,
            callback : callback,//options,success,response 
            scope: scope
        });
     },
     loadJsonStore:function( url, params,model, callback, scope) {
        Ext.create('Ext.data.Store', {
             model: model,
             proxy: {
                 type: 'ajax',
                 url: url,//App.Setting.getHostUrl() + '/' + url,
                 extraParams: params,
                 reader: {
                     type: 'json',
                     root: 'data'
                 }
             }
         }).load({ callback: callback, scope: scope});
     },
//     UserLogin:function(username,password, callback, scope){
//         this.AjaxRequest('login',{username:username , password:password },callback, scope);
//     },
     DownloadFile:function(username, filename, callback, scope){
         this.AjaxRequest('downloadfile',{username:username, filename:filename},callback, scope);
     },
//     User
     UserGet:function(webapp_id,callback, scope){
          this.AjaxRequest('getuserinfo',{webapp_id:webapp_id},callback, scope);
//          alert('xx');
     },
//     UserUpdate:function(user_id, user, callback, scope){
//          this.AjaxRequest('updateuser',{user_id:user_id,user:user},callback, scope)
//     },
//     GetProfile:function(user_id,profile_id, callback, scope){
//         this.AjaxRequest('getprofile',{user_id:user_id , profile_id:profile_id },callback, scope);
//     }
    }
});

