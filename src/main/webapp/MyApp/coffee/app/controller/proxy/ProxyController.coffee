Ext.define 'MyApp.controller.proxy.ProxyController',
	extend: 'Ext.app.ViewController'
	alias: 'controller.proxy'
	requires: ['MyApp.view.proxy.Add']
	addProxySetting: ->
		# 弹出添加配置窗口
		proxyaddwin = Ext.create "MyApp.view.proxy.Add",
			modal : true
			region: 'center'
			floating: true
			closable : true
			draggable: true
		proxyaddwin.show()