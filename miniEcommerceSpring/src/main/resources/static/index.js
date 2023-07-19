const vlu = document.getElementById('send')
// const a = document.getElementById('a')
// const b= document.getElementById('b')
// const va = a.nodeValue
// const vb = b.nodeValue

vlu.addEventListener('click',()=>{
     const produto=document.getElementById('nome')
     const preco = document.getElementById('preco')
     const desconto = document.getElementById('desconto')
     const value = produto.value
     const vp = preco.value
     const vd=desconto.value
     const Produto ={
          nome:value,
          preco:vp,
          desconto:vd
     }
     fetch('http://localhost:8080/pr', {
          method:'POST',
          mode: 'cors',
          headers : {
               'content-type': '/cad/json'
          },
          body: JSON.stringify(Produto)
     }).then(res => res.json()).then(json=>console.log(json))
     .catch(err => console.log(err));
})