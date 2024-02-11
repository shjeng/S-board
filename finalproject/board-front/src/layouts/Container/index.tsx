import React from 'react';
import Header from 'layouts/Header';
import Footer from 'layouts/Footer';
import { Outlet, useLocation } from 'react-router-dom';
import { AUTH_PATH } from 'constant';

//    component: 레이아웃           //
const Container = () => {

  const {pathname} = useLocation(); // useLocation() : 현재 경로를 가져옴 

  //    render: 레이아웃 렌더링           //
  return (
    <>
      <Header/>
      <Outlet/>
      {pathname !== AUTH_PATH() &&<Footer/>}
    </>
  );
};

export default Container;