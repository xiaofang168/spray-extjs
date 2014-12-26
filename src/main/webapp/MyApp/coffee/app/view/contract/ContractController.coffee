Ext.define 'MyApp.view.contract.ContractController',
	extend: 'Ext.app.ViewController'
	alias: 'controller.contract'
	requires: [
		'MyApp.view.contract.Add'
	]
	
	add:(button) ->
		# 弹出添加配置窗口
		#console.log @lookupReference('proxygrid')
		#console.log button.up('proxylist')
		addwin = Ext.create "MyApp.view.contract.Add",
			modal : true
			region: 'center'
			floating: true
			parent: button.up('contractlist')
			closable : true	
			draggable: true
		addwin.show()
	
	save: ->
		# 验证form表单
		addview = @getView()
		if addview.isValid()
			gridpanel = addview.parent.down('gridpanel')
			contract = addview.getForm().getValues()
			proxyModel = Ext.create('MyApp.model.contract.ExportContractProgress', contract)
			proxyModel.set('id', 0)
			proxyModel.save
				success: (record, operation) ->
					Ext.Msg.alert("提示", "添加成功！")
					addview.hide()
					gridpanel.getStore().reload()
				failure: (record, operation) ->
					Ext.Msg.alert("提示", "添加失败！")

	update: ->
		updateview = @getView()
		if updateview.isValid()
			gridpanel = updateview.parent.down('gridpanel')
			contract = updateview.getForm().getValues()
			contract.id = parseInt(contract.id)
			contractModel = Ext.create 'MyApp.model.contract.ExportContractProgress',
				id: contract.id
			for k,v of contract
				contractModel.set(k,v)
			contractModel.save
				success: (record, operation) ->
					Ext.Msg.alert("提示", "修改成功！")
					updateview.hide()
					gridpanel.getStore().reload()
				failure: (record, operation) ->
					Ext.Msg.alert("提示", "修改失败！")

	show: (grid, td, cellIndex, record) ->
		# Get the Record for the row
			#Get field name for the column
			if cellIndex is 4
				# 打开详情窗口
				detailwin = Ext.create "MyApp.view.contract.Detail",
					modal : true
					region: 'center'
					floating: true
					closable : true	
					draggable: true
				MyApp.model.contract.ExportContractProgress.load record.data.id,
					success: (store) ->
						detailwin.getForm().setValues(store.data)
						detailwin.show()

	edit: (button)->
		selectionModel = button.up('contractlist').down('gridpanel').getSelectionModel()
		selectionModelSize = selectionModel.getSelection().length
		if selectionModelSize is 0
			Ext.Msg.alert("提示", "请选择一条记录！")
		else if selectionModelSize > 1
			Ext.Msg.alert("提示", "只能选择一条记录！")
		else
			id = selectionModel.getSelection()[0].data.id
			# 根据id查询详细信息
			editwin = Ext.create "MyApp.view.contract.Edit",
				modal : true
				region: 'center'
				floating: true
				parent: button.up('contractlist')
				closable : true	
				draggable: true
			MyApp.model.contract.ExportContractProgress.load id,
				success: (store) ->
					editwin.getForm().setValues(store.data)
			editwin.show()
	
	delete: ->
		contractgrid = @lookupReference('contractgrid')
		selectionModel = contractgrid.getSelectionModel()
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
						url: "#{MyApp.context}app/contract/#{idStr}"
						method: 'delete'
						success: (response, opts) ->
							#obj = Ext.decode(response.responseText)
							contractgrid.getStore().reload()
						failure: (response, opts) ->
							console.log("server-side failure with status code #{response.status}")

	exportExcel: ->
		window.location.href = "#{MyApp.context}app/contract/_export" 