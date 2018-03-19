window.org_vaadin_simplemde_SimpleMarkdownEditor = function () {

  var elem = this.getElement(), self = this
  var simplemde
  var timeout

  this.setMarkdownText = function (text) {
    if (typeof simplemde !== 'undefined') {
      simplemde.value(text)
    }
  }

  this.onStateChange = function () {

    var state = this.getState()

    if (typeof simplemde === 'undefined') {
      textarea = document.createElement('textarea')
      textarea.classList.add('simple-mde-textarea')

      elem.appendChild(textarea)

      var hideIcons = []
      if (typeof state.hideIcons !== 'undefined') {
        state.hideIcons.forEach(function (icon) {
          hideIcons.push(icon.toLowerCase()
                             .replace(/\_/gi, '-'))
        })
      }

      simplemde = new SimpleMDE({
        element: textarea,
        status: (state.showStatus ? ['lines', 'words', 'cursor'] : false),
        spellChecker: state.spellChecker,
        lineWrapping: state.lineWrapping,
        initialValue: state.markdownText,
        hideIcons: hideIcons
      })

      if (!state.showStatus) {
        elem.classList.add('hidden-status')
      }

      simplemde.codemirror.on('change', function () {
        clearTimeout(timeout)
        timeout = setTimeout(function () {
          console.log()
          self.getRpcProxy()
              .textChanged(simplemde.value())
        }, state.changeTimeOut)
      })
    }

  }

}