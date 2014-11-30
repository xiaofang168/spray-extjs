###定义了一个背景透明的Button类，继承于Button ###  
Ext.define 'MyApp.ux.ButtonTransparent',
	extend : 'Ext.button.Button' # 继续于Ext.button.Button   
	alias : 'widget.buttontransparent' #此类的xtype类型为buttontransparent 
	#类初始化时执行   
	initComponent : ->
		#设置事件监听   
		@listeners =
			#鼠标移开,背景设置透明   
			mouseout : -> 
				@setTransparent(document.getElementById(@id))
			#鼠标移过,背景取消透明   
			mouseover : ->
				b = document.getElementById(@id)
				b.style.backgroundImage = ''
				b.style.backgroundColor = ''
				b.style.borderColor = ''
			#背景设置透明   
			afterrender: ->
				@setTransparent(document.getElementById(@id))
		@callParent(arguments) # 调用你模块的initComponent函数
	setTransparent : (b) ->
		b.style.backgroundImage = 'inherit'
		b.style.backgroundColor = 'inherit'
		b.style.borderColor = 'transparent'