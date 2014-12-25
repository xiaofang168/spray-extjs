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
		addview = @getView()
		gridpanel = addview.parent.down('contractgrid')
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
	
	openDetails: (grid, td, cellIndex, record) ->
		# Get the Record for the row
			#Get field name for the column
			if cellIndex is 4
				# 打开详情窗口
				id = record.data.id
				console.log id