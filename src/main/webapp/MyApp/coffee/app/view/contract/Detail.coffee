
Ext.define 'MyApp.view.contract.Detail',
	extend : 'Ext.form.Panel'
	alias : 'widget.contractdetail'
	collapsible: true  #可折叠
	autoScroll: true  #自动创建滚动条
	title: '出口合同进程详情'
	width: 900
	height: 520
	layout: 'form'
	defaults:
		xtype: 'container'
		layout: 'hbox'
		defaultType: 'displayfield'
	fieldDefaults:
		labelSeparator :'：'#分隔符
		labelWidth: 120   
		labelAlign: 'right'
		autoScroll: true
		flex: 1
		margin: 5
		margin: '15 0 15 0' #(top, right, bottom, left).
	items:[
		items:[
			fieldLabel:'月份'
			name:'month'
		,
			fieldLabel:'出口合同签订日期'
			name:'exportContractDate'
		,
			fieldLabel:'出口合同号'
			name:'exportContractNum'
		]
	,
		items: [
			fieldLabel: '合同状态'
			name: 'contractStatus'
		,
			fieldLabel: '客户方'
			name:'customer'
		,
			fieldLabel: '国别'
			name:'country'
		]
	,
		items: [
			fieldLabel: '合同产品'
			autoScroll: true
			name:'product'
		,
			
			fieldLabel: '出口合同金额'
			name:'exportContractMoney'
		,
			fieldLabel: '结算方式'
			name:'settlementWay'
		]
	,
		items: [
			fieldLabel: 'LC最晚交货期'
			name:'latestDeliveryDate'
		,
			fieldLabel: '运输方式及箱型'
			name:'transportWay'
		,
			fieldLabel: '采购合同号'
			name:'purchaseContractNum'
		]
	,
		items: [
			fieldLabel: '采购合同金额'
			name:'purchaseMoney'
		,
			fieldLabel: '合同进展'
			name:'progressDescription'
		,
			fieldLabel: '预计出厂日'
			name:'planLeaveFactoryDate'
		]
	,
		items: [
			fieldLabel: '预计发货日'
			name:'planSendDate'
		,
			fieldLabel: '预计到港日'
			name:'planArrivalData'
		,
			fieldLabel: '是否已到款'
			name:'payDescription'
		]
	items: [
			fieldLabel: '备注'
			name:'comment'
		]
	]
	buttons:[
			text: '关闭'
			handler: ->
				@up('contractdetail').hide()
	]
	buttonAlign : 'center'