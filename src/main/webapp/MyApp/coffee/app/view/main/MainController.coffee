Ext.define 'MyApp.view.main.MainController',
	extend: 'Ext.app.ViewController'
	requires: [
		'Ext.MessageBox'
		'MyApp.view.proxy.List'
	]
	alias: 'controller.main'
	onClickButton: ->
		Ext.Msg.confirm('Confirm', 'Are you sure?', 'onConfirm', @)
	onConfirm: (choice) ->
		if choice is 'yes'
			# 获取对应组件的viewModel
			# @getView().down('mainmenuaccordion').getViewModel().set('name' ,"修改后的title")
			@getView().getViewModel().set('name' ,"修改后的title")
	onMainMenuClick:(menuitem) ->
		maincenter = @getView().down('maincenter')
		maincenter.setActiveTab(maincenter.add(
			xtype : menuitem.view
		))


