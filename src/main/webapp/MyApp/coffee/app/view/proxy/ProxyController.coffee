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
		addview = @getView()
		gridpanel = addview.parent.down('gridpanel')
		proxy = addview.getForm().getValues()
		proxy.id = 0
		Ext.Ajax.request
				url: "#{MyApp.context}app/proxy"
				method: 'post'
				jsonData: proxy
				success: (response, opts) ->
					Ext.Msg.alert("提示", "添加成功！")
					addview.hide()
					gridpanel.getStore().reload()
				failure: (response, opts) ->
					Ext.Msg.alert("提示", "添加失败！")
					console.log("server-side failure with status code #{response.status}")

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
			# 根据id查询详细信息
			proxyeditwin = Ext.create "MyApp.view.proxy.Edit",
				modal : true
				region: 'center'
				floating: true
				parent: button.up('proxylist')
				closable : true	
				draggable: true
			Ext.Ajax.request
				url: "#{MyApp.context}app/proxy/#{id}"
				method: 'get'
				success: (response, opts) ->
					obj = Ext.decode(response.responseText)
					proxyeditwin.getForm().setValues(obj)
				failure: (response, opts) ->
					console.log("server-side failure with status code #{response.status}")
			proxyeditwin.show()

	delete: ->
		proxygrid = @lookupReference('proxygrid')
		selectionModel = proxygrid.getSelectionModel()
		selectionModelSize = selectionModel.getSelection().length
		if selectionModelSize is 0
			Ext.Msg.alert("提示", "请至少选择一条记录！")
		else
			deleteIds = []
			for selection in selectionModel.getSelection()
				deleteIds.push(selection.data.id)
			idStr = deleteIds.join(",")
			Ext.Msg.confirm '确认', '是否要删除选中的记录?', (choice)->
				if choice is 'yes'
					Ext.Ajax.request
						url: "#{MyApp.context}app/proxy/#{idStr}"
						method: 'delete'
						success: (response, opts) ->
							obj = Ext.decode(response.responseText)
							proxygrid.getStore().reload()
						failure: (response, opts) ->
							console.log("server-side failure with status code #{response.status}")