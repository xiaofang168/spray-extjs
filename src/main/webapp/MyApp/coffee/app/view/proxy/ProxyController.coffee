Ext.define 'MyApp.view.proxy.ProxyController',
	extend: 'Ext.app.ViewController'
	alias: 'controller.proxy'
	requires: ['MyApp.view.proxy.Add']

	addProxySetting:(button) ->
		# 弹出添加配置窗口
		#console.log @lookupReference('proxygrid')
		#console.log button.up('proxylist')
		proxyaddwin = Ext.create "MyApp.view.proxy.Add",
			modal : true
			region: 'center'
			floating: true
			parent: button.up('proxylist')
			closable : true	
			draggable: true
		proxyaddwin.show()

	save: ->
		@getView().parent.down('gridpanel').getStore().sync()