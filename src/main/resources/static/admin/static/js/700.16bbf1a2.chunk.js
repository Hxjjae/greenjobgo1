"use strict";(self.webpackChunkgreenjobgo_admin=self.webpackChunkgreenjobgo_admin||[]).push([[700],{700:(e,t,s)=>{s.r(t),s.d(t,{StudentPageAtom:()=>E,default:()=>f});var a=s(7313),l=s(8467),c=s(78),n=s(451),o=s(9024),d=s(2085),u=s(2872),r=s(5044),i=s(5374),x=s(5743),h=s(3564),p=s(7391),j=s(6702),g=s(6417);c.zl.RECOIL_DUPLICATE_ATOM_KEY_CHECKING_ENABLED=!1;const{persistAtom:S}=(0,n.J)(),E=(0,c.cn)({key:"StudentPageAtom",default:{page:1,count:0,search:"",category:""},effects_UNSTABLE:[S]}),f=()=>{(0,l.s0)();const[e,t]=(0,a.useState)(!1),[s,n]=(0,a.useState)(""),[S,f]=(0,a.useState)(!1),[k,y]=(0,a.useState)([]),[O,m]=(0,a.useState)(!1),[C,M]=(0,a.useState)(!1),[A,_]=(0,a.useState)(!1),[b,w]=(0,a.useState)(!1),[F,N]=(0,a.useState)(!1),[v,L]=(0,a.useState)(!1),[T,Z]=(0,a.useState)(null),[D,I]=(0,a.useState)(null),[P,R]=(0,a.useState)(!1),[U,B]=(0,a.useState)(""),[K,z]=(0,a.useState)(""),[G,H]=(0,c.FV)(E),{page:J,count:V,search:Y,category:q,render:Q}=G,W=()=>{(0,r.TF)(y,H,J,Y,q,f,n)};return(0,a.useEffect)((()=>{t(!!s)}),[s]),(0,a.useEffect)((()=>{W()}),[J,V]),(0,g.jsxs)(i.Tu,{children:[(0,g.jsx)("div",{className:"student-title",children:(0,g.jsx)("h3",{children:"\uc218\uac15\uc0dd \ub4f1\ub85d \xb7 \uad00\ub9ac"})}),(0,g.jsxs)(i.Ah,{children:[(0,g.jsx)(o.Z,{category:q,handleCategoryFiiter:e=>{H((t=>({...t,category:e.target.value,search:""})))},search:Y,handleSearch:async()=>{H((e=>({...e,page:1,category:q,search:Y}))),await W()}}),O&&(0,g.jsx)(x.iI,{modalOpen:O,setModalOpen:m}),F&&(0,g.jsx)(h.py,{acceptOkModal:F,setAcceptOkModal:N,uploadResult:v}),A&&(0,g.jsx)(x.r7,{excelModalOpen:A,setExcelModalOpen:_,handleExcelUpload:async()=>{if(R(!0),T){let t=new FormData;t.append("studentfile",T);try{const e=await(0,r.xw)(t,n);R(!1),L(e),e.success&&(_(!1),Z(null)),W()}catch(e){console.error("\ud30c\uc77c \uc5c5\ub85c\ub4dc\uc5d0 \uc2e4\ud328\ud588\uc2b5\ub2c8\ub2e4.",e)}}},selectedFile:T,setSelectedFile:Z,excelOkModal:b,setExcelOkModal:w,isLoading:P}),b&&(0,g.jsx)(h.x3,{excelOkModal:b,setExcelOkModal:w,uploadResult:v}),(0,g.jsxs)("div",{className:"student-buttons",children:[(0,g.jsx)("button",{onClick:async()=>{(0,r.ou)(I,n)},children:"\uc5d1\uc140 \ub2e4\uc6b4\ub85c\ub4dc"}),(0,g.jsx)("button",{onClick:()=>{_(!0)},children:"\uc5d1\uc140 \uc5c5\ub85c\ub4dc"})]}),(0,g.jsx)("div",{className:"total-count",children:(0,g.jsxs)("span",{children:["\ucd1d ",V,"\uba85"]})}),(0,g.jsxs)(i.E6,{children:[S&&(0,g.jsx)(p.Z,{}),(0,g.jsx)(d.Z,{listData:k,page:J})]}),(0,g.jsx)(u.Z,{page:J,count:V,pgge:J})]}),e&&(0,g.jsx)(j.Z,{header:"",open:e,close:()=>{t(!1),n("")},onConfirm:()=>{t(!1),n("")},children:(0,g.jsx)("span",{children:s})})]})}}}]);