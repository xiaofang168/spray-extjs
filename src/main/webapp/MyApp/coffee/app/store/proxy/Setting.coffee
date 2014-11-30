Ext.define 'MyApp.store.proxy.Setting', 
	extend: 'Ext.data.Store'
	model: 'MyApp.model.proxy.Setting'
	alias: 'settingstore'
	data: [
		app_name: 'app2', ip: 'localhost', is_enable :'1'
	,
		app_name: 'app1', ip: 'localhost', is_enable :'1'
	]
