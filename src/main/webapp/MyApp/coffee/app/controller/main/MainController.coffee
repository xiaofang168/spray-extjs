Ext.define 'MyApp.controller.main.MainController',
	extend: 'Ext.app.ViewController'
	requires: ['Ext.MessageBox','MyApp.view.proxy.List']
	alias: 'controller.main'
	onClickButton: ->
		Ext.Msg.confirm('Confirm', 'Are you sure?', 'onConfirm', @)
	onConfirm: (choice) ->
		if choice is 'yes' 
			@getView().getViewModel().set('name' ,"修改后的title")
	onMainMenuClick:(menuitem) ->
		maincenter = @getView().down('maincenter')
		maincenter.setActiveTab(maincenter.add(
			xtype : 'proxylist'
		))


