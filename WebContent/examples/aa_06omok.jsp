<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String pan = request.getParameter("pan");
%>    
    
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>three.js webgl - geometry - cube</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
		<style>
			body {
				margin: 0px;
				background-color: #000000;
				overflow: hidden;
			}
		</style>
	</head>
	<body id="my_body" onload="fn_load()" >

		<script src="../build/three.js"></script>
		<script src="jquery-1.12.4.js"></script>
		<script src="js/controls/OrbitControls.js"></script>

		<script>

			var camera, scene, renderer;
			var mesh;
			var mesh1;
			
			var texture1;
			var geometry1;
			var material1;

			var texture2;
			var geometry2;
			var material2;
	
			function fn_load() {
				
			init();
			animate();
			fn_ajax2();
		//	fn_add_Stone();
			
			}
			
			function init() {

				camera = new THREE.PerspectiveCamera( 70, window.innerWidth / window.innerHeight, 1, 1000 );
				camera.position.z = 700;
				camera.rotation.z = Math.PI;
				

				scene = new THREE.Scene();

				var texture  = new THREE.TextureLoader().load('omok.png');
				var geometry = new THREE.PlaneBufferGeometry( 600, 600, 4, 4 );
				var material = new THREE.MeshBasicMaterial( {map:texture } );
				mesh = new THREE.Mesh( geometry ,material);

				texture1  = new THREE.TextureLoader().load('black.png');
				geometry1 = new THREE.CylinderBufferGeometry( 20, 20, 5, 40, 40 );
				material1 = new THREE.MeshBasicMaterial( { map:texture1 } );
				
				texture2  = new THREE.TextureLoader().load('white.png');
				geometry2 = new THREE.CylinderBufferGeometry( 20, 20, 5, 40, 40 );
				material2 = new THREE.MeshBasicMaterial( { map:texture2 } );				
				
				scene.add(mesh);
				
				mesh1     = new THREE.Mesh( geometry1 ,material1); 
				mesh1.position.x = Math.PI*0.5; 
				mesh1.position.x = -270; 
				mesh1.position.y = 270;
				mesh1.position.z = 2.5;
				scene.add(mesh1);
				
				mesh2     = new THREE.Mesh( geometry2 ,material2); 
				mesh2.position.x = Math.PI*0.5; 
				mesh2.position.x = 270; 
				mesh2.position.y = 270;				
				mesh2.position.z = 2.5;
				scene.add(mesh2);
				

				renderer = new THREE.WebGLRenderer( { antialias: true } );
				renderer.setPixelRatio( window.devicePixelRatio );
				renderer.setSize( window.innerWidth, window.innerHeight );
				document.body.appendChild( renderer.domElement );



				window.addEventListener( 'resize', onWindowResize, false );
				
				var orbit = new THREE.OrbitControls( camera, renderer.domElement ); // 시점 변경
	            orbit.update();
			}

			function onWindowResize() {

				camera.aspect = window.innerWidth / window.innerHeight;
				camera.updateProjectionMatrix();

				renderer.setSize( window.innerWidth, window.innerHeight );

			}
			
			var index_scene = 0;
			var index_omok = 0;
			function animate() {
//				console.log("index_scene : " + index_scene);
				index_scene++;

				requestAnimationFrame( animate );
				
				if((index_scene%60) == 0){
					fn_draw_onestone();
					index_omok++;
				}
								

 			    mesh1.rotation.x =1.5;
 				mesh2.rotation.x =1.5;
 
				renderer.render( scene, camera );

			}
			
			function fn_draw_onestone() {
				var str = arrhist[index_omok];
				
				var ii = -1;
				var jj = -1;
				var int_status = -1;
				var int_temp   = -1;
				
				if(index_omok ==0){
					for(var i = 0; i < str.length; i++){
						var str_temp = str.substring(i, i+1);
						if(str_temp > 0){
							int_status = Number(str_temp);
							int_temp = i;
							break;
						}
					}
				}else if(index_omok < arrhist.length){
					var str_pre = arrhist[index_omok-1];
					for(var i = 0; i < str.length; i++){
						var str_temp     = str.substring(i, i+1);
						var str_temp_pre = str_pre.substring(i, i+1);
						
						if(str_temp != str_temp_pre){
							int_status = Number(str_temp);
							int_temp = i;
							break;
						}
					}
				}else{
					return;
				} 
					
				ii = parseInt(int_temp / 10);
				jj = int_temp % 10;
				
				fn_add_Stone(ii,jj,int_status);
			}
			
			var pan = <%=pan%>;
			var arrhist = new Array();
			
			function fn_ajax2(){
				alert("pan : " +pan);
				$.ajax({
					
					type : "get",
					url : "http://127.0.0.1/GAMESOCKET/mypan_jsonp",
					data : { pan: pan},
					dataType : "jsonp",
					jsonpCallback: "myPan",
					
					
					success : function(data) {
						for (var i = 0; i < data.length; i++) {
							arrhist.push(data[i].history);
						}
						console.log(arrhist);			
						
					},
					error : function(xhr, status, error) {
						console.log("에러!: " + error);

					},
				});		
			}
			
			
			function fn_add_Stone(ii,jj,int_status) {
				
 				var start_X = -270;
				var start_Y =  270;
				var end_X   =  270;				
				var end_Y   = -270;
				
				var betweenX = (end_X-start_X)/(10-1);
				var betweenY = (end_Y-start_Y)/(10-1);

				var meshTemp ;
				if(int_status == 1){
					meshTemp = new THREE.Mesh( geometry1 ,material1); 
				}else if(int_status == 2){
					meshTemp = new THREE.Mesh( geometry2 ,material2); 
				}
				
				meshTemp.position.x = Math.PI*0.5; 
				meshTemp.position.x = start_X + (jj *betweenX ); 
				meshTemp.position.y = start_Y + (ii *betweenY );
				meshTemp.position.z = 2.5;
				meshTemp.rotation.x = 1.5;
				scene.add(meshTemp);
						
			}
				

				
				
				
			
			
		</script>
	</body>
</html>