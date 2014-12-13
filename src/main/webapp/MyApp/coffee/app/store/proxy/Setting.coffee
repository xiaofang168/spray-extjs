Ext.define 'MyApp.store.proxy.Setting', 
	extend: 'Ext.data.Store'
	model: 'MyApp.model.proxy.Setting'
	autoLoad: true
	alias: 'settingstore'
	proxy:
		type: 'ajax'
		url:  "#{MyApp.context}app/proxy"
		reader:
			type: 'json'
			rootProperty: 'users'
