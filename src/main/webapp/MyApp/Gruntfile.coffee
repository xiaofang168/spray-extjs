module.exports = (grunt) ->
	grunt.initConfig
		pkg: grunt.file.readJSON 'package.json'

		coffee:
			options:
					bare: true # Compile the JavaScript without the top-level function safety wrapper
			compile:
				expand: true,
				flatten: false, #here, it must be false to keep folder structure when compiling
				cwd: 'coffee',
				src: 'app/**/*.coffee',
				dest: '.',
				ext: '.js'

		copy:
			data:
				expand: true
				cwd: 'coffee'
				flatten: false
				src: 'app/**/*.json'
				dest: '.'

		clean:
			app:
				src: 'app/**/*.js'

		watch:
			coffee:
				files: ['coffee/app/**/*.coffee','coffee/app/**/*.json']
				tasks: ['clean:app','coffee:compile','copy:data']

	grunt.loadNpmTasks 'grunt-contrib-coffee'
	grunt.loadNpmTasks 'grunt-contrib-watch'
	grunt.loadNpmTasks 'grunt-contrib-clean'
	grunt.loadNpmTasks 'grunt-contrib-copy'

	grunt.registerTask 'default',['clean:app','coffee:compile','copy:data','watch']