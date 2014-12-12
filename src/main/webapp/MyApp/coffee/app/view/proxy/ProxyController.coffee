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
	
	update: ->
		alert('update')

	edit: (button)->
		selectionModel = button.up('proxylist').down('gridpanel').getSelectionModel()
		selectionModelSize = selectionModel.getSelection().length
		if selectionModelSize is 0
			Ext.Msg.alert("提示", "请选择一条记录！")
		else if selectionModelSize > 1
			Ext.Msg.alert("提示", "只能选择一条记录！")
		else
			id = selectionModel.getSelection()[0].data.id
			console.log id
			proxyeditwin = Ext.create "MyApp.view.proxy.Edit",
				modal : true
				region: 'center'
				floating: true
				parent: button.up('proxylist')
				closable : true	
				draggable: true
			proxyeditwin.show()

	delete: ->
		console.log @lookupReference('proxygrid')