import { FavoriteListItem } from 'types/interface';
import './style.css';
import defaultProfileImage from 'assets/image/default-profile-image.png';

import React from 'react';

interface Props{
  favoriteListItem: FavoriteListItem;
}

// component: Favorite List Item 컴포넌트
const FavoritItem = ({favoriteListItem}: Props) => {

  // properties
  const {profileImage, nickname} = favoriteListItem;

  // render : Favorite List Item 렌더링
  return (
    <div className='favorite-list-item'>
      <div className='favorite-list-item-profile-box'>
        <div className='favorite-list-item-profile-image' style={{backgroundImage: `url(${profileImage ? profileImage : defaultProfileImage})`}}></div>
      </div>
      <div className='favorite-list-item-nickname'>{nickname}</div>
    </div>
  );
};

export default FavoritItem;