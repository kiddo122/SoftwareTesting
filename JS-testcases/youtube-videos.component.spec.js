'use strict';

import YoutubeVideosModule, { YoutubeVideosComponent } from './';
import YoutubeVideosMock from '../../../tests/mocks/youtube.videos.mock';
import VideoItemMock from '../../../tests/mocks/video.item.mock';

describe('Youtube Videos', () => {
  let scope, YoutubeSearch, ctrl, YoutubePlayerSettings, controller;
  let PlaylistInfo = {};
  let mockVideoItem = {};
  let mockPlaylistItem = {};

  beforeEach(window.module(YoutubeVideosModule));

  beforeEach(window.inject(($injector, $controller, $q) => {
    controller = $controller;
    YoutubeSearch = jasmine.createSpyObj('YoutubeSearch', [
      'search', 'resetPageToken', 'getFeedType', 'searchMore'
    ]);
    YoutubePlayerSettings = jasmine.createSpyObj('YoutubePlayerSettings',
      ['playVideoId', 'queueVideo', 'playPlaylist']);
    // spies
    YoutubeSearch.items = [];
    PlaylistInfo.list = () => {};
    spyOn(PlaylistInfo, 'list').and.callFake( () => {
      let defer = $q.defer();
      defer.resolve();
      return defer.promise;
    });
    scope = $injector.get('$rootScope').$new();
    ctrl = controller(YoutubeVideosComponent.controller, {
      $scope: scope,
      YoutubeSearch: YoutubeSearch,
      YoutubePlayerSettings: YoutubePlayerSettings,
      PlaylistInfo: PlaylistInfo
    });
    scope.$digest();
    mockVideoItem = Object.assign(VideoItemMock);
    mockPlaylistItem = Object.assign(YoutubeVideosMock);
  }));

  it('search youtube once when it loads if there are no items to render', () => {
    expect(YoutubeSearch.search).toHaveBeenCalled();
    expect(YoutubeSearch.search.calls.count()).toBe(1);
  });

  it('should not search when it loads if there are items to render', () => {
    angular.copy(mockPlaylistItem.items, YoutubeSearch.items);
    controller(YoutubeVideosComponent.controller, {
      $scope: scope,
      YoutubeSearch: YoutubeSearch,
      YoutubePlayerSettings: YoutubePlayerSettings,
      PlaylistInfo: PlaylistInfo
    });
    expect(YoutubeSearch.search.calls.count()).toBe(1);
  });

  it('should queue and play video', () => {
    ctrl.playVideo(mockVideoItem);
    expect(YoutubePlayerSettings.queueVideo).toHaveBeenCalled();
    expect(YoutubePlayerSettings.playVideoId).toHaveBeenCalled();
  });

  it('should play a playlist and queue the videos', () => {
    ctrl.playPlaylist(mockPlaylistItem);
    scope.$digest();
    expect(YoutubePlayerSettings.playPlaylist.calls.count()).toBe(1);
  });

// WE MADE
  it('(CPEN422) should show the filters for videos', () => {
    ctrl.toggleFilters();
    scope.$digest();
    expect(ctrl.hideFilters).toBeFalsy();
  });

it('(CPEN422) should show up in playlist if queued', () => {
    ctrl.queueVideo(mockVideoItem);
    expect(YoutubePlayerSettings.queueVideo).toHaveBeenCalled();

  });


});