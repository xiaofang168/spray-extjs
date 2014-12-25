Ext.define 'MyApp.store.contract.ExportContractProgress', 
	extend: 'Ext.data.Store'
	model: 'MyApp.model.contract.ExportContractProgress'
	autoLoad: true
	alias: 'exportContractProgressStore'
	pageSize: "#{MyApp.pageSize}"
	remoteSort: true
	sorters: [
		property: 'id'
		direction: 'DESC'
	]
	proxy:
		type: 'ajax'
		url: "#{MyApp.context}app/contract"
		startParam: '$offset'
		limitParam: '$limit'
		sortParam: '$sort'
		pageParam : '$page'
		filterParam: '$filter'
		# if override actionMethods use post get(read) datas
		#paramsAsJson: true
		reader:
			type: 'json'
			totalProperty: 'total'
			rootProperty: 'results'
