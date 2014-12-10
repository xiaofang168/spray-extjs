Ext.define 'MyApp.store.proxy.Setting', 
	extend: 'Ext.data.Store'
	model: 'MyApp.model.proxy.Setting'
	autoLoad: true
	alias: 'settingstore'
	proxy:
		type: 'ajax'
		url: 'app/store/proxy/users.json'
		reader:
			type: 'json'
			rootProperty: 'users'
