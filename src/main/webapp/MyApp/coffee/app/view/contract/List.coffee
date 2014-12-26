Ext.define 'MyApp.view.contract.List',
	extend : 'Ext.panel.Panel'
	requires: [
		'Ext.grid.Panel'
		'Ext.button.Button'
		'Ext.toolbar.Spacer'
		'Ext.toolbar.Fill'
		'Ext.selection.CheckboxModel'
		'MyApp.store.contract.ExportContractProgress'
		'Ext.toolbar.Paging'
		'MyApp.view.contract.ContractController'
	]
	alias : 'widget.contractlist'
	controller: 'contract'
	closeAction : 'hide'
	autoDestroy : false
	tabPosition : 'top'
	title: '出口合同进程列表'
	closable : true
	reorderable : true
	tbar:[
		xtype: 'button'
		text: '添加'
		glyph: 0xf0fe
		handler : 'add'
	,
		xtype : 'tbspacer'
		width: 20
	,
		xtype: 'button'
		text: '修改'
		glyph: 0xf044
		handler: 'edit'
	,
		xtype : 'tbspacer'
		width: 20
	,
		xtype: 'button'
		text: '删除'
		glyph: 0xf00d
		handler: 'delete'
	,
		xtype: 'tbfill'
	,
		xtype: 'button'
		text: '导出Excel'
		glyph: 0xf1c3
		handler: 'exportExcel'
	]
	initComponent: ->
		contractstore = Ext.create('MyApp.store.contract.ExportContractProgress')
		@items = [
			xtype: 'gridpanel'
			forceFit: true
			loadMask: true #读取数据遮罩和提示,Loading....
			selType: 'checkboxmodel'
			selModel:
				injectCheckbox: 0
				mode: 'SIMPLE' #"SINGLE"/"SIMPLE"/"MULTI"
				checkOnly: true
			reference: 'contractgrid'
			region: 'center'
			columns: [
				xtype:'rownumberer'
				sortable: false
				text:'序号'
				width: 60
			,
				hidden : true
				dataIndex: 'id'
				hideable: false
			,
				text: '月份'
				sortable: true
				dataIndex: 'month'
			,
				text: '出口合同号'
				dataIndex: 'exportContractNum'
				renderer: (val) ->
					"<a href='#'>#{val}</a>"
			,
				text: '合同状态'
				dataIndex: 'contractStatus'
			,
				text: '客户方'
				dataIndex: 'customer'
			,
				text: '国别'
				dataIndex: 'country'
			,
				text: '合同产品'
				dataIndex: 'product'
			,
				text: '出口合同金额'
				dataIndex: 'exportContractMoney'
			,
				text: '结算方式'
				dataIndex: 'settlementWay'
			]
			store: contractstore
			dockedItems: [
				xtype: 'pagingtoolbar'
				store: contractstore 
				dock: 'bottom'
				displayInfo: true
			]
			listeners:
				cellclick: 'show'
		]
		@callParent()