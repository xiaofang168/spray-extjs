Ext.define 'MyApp.store.proxy.Setting', 
	extend: 'Ext.data.Store'
	model: 'MyApp.model.proxy.Setting'
	autoLoad: true
	alias: 'settingstore'
	pageSize: "#{MyApp.pageSize}"
	remoteSort: true
	sorters: [
		property: 'id'
		direction: 'DESC'
	]
	proxy:
		type: 'ajax'
		url: "#{MyApp.context}app/proxy"
		reader:
			type: 'json'
			rootProperty: 'users'
