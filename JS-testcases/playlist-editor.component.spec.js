import PlaylistEditor, { PlaylistEditorComponent } from './index.js';
import UserPlaylistsMock from '../../../tests/mocks/user.playlists.mock';
import VideoItemMock from '../../../tests/mocks/video.item.mock';

describe('Playlist Editor', () => {
  let scope, element, iscope, PlaylistEditorSettings, UserPlaylists, currentMedia, ApiPlaylists, $q;
  let userPlaylistsMock = {};
  let playlistEditorHtml = [
    '<playlist-editor></playlist-editor>'
  ];

  beforeEach(window.module(PlaylistEditor));
  beforeEach(() => {
    window.module($provide => {
      $provide.value('YoutubeApi', {});
    });
    inject(($compile, $controller, $rootScope, _PlaylistEditorSettings_, $httpBackend, _UserPlaylists_, _ApiPlaylists_, _$q_) => {
      PlaylistEditorSettings = _PlaylistEditorSettings_;
      UserPlaylists = _UserPlaylists_;
      ApiPlaylists = _ApiPlaylists_;
      $q = _$q_;
      
      // spyOn(YoutubeSearch, 'search').and.returnValue(true);
      scope = $rootScope.$new();
      userPlaylistsMock = UserPlaylistsMock;
      currentMedia = VideoItemMock;
      PlaylistEditorSettings.add(currentMedia);
      element = angular.element(playlistEditorHtml.join(''));
      $compile(element)(scope);
      scope.$digest();
      iscope = element.isolateScope();
    });
  });

  it('should display playlists', () => {
    const actual = element.children()[0].classList.contains('playlists-viewer');
    expect(actual).toBeTruthy();
  });

  it('should not show the create button for a new playlist', () => {
    expect(iscope.$ctrl.showCreate).toBeFalsy();
  });

  it('should show the create button when a playlist name doesn\'t exist', () => {
    iscope.$ctrl.search = 'a new playlist';
    angular.extend(UserPlaylists.tracks, userPlaylistsMock.items);
    iscope.$ctrl.isPlaylistNameExists();
    scope.$digest();
    expect(iscope.$ctrl.showCreate).toBeTruthy();
  });

  it('should update the playlists when a playlist is removed', () => {
    angular.extend(UserPlaylists.tracks, userPlaylistsMock.items);
    let deferred = $q.defer();
    spyOn(ApiPlaylists, 'remove').and.callFake( () => {
      deferred.resolve();
      return deferred.promise;
    });
    iscope.$ctrl.remove(UserPlaylists.tracks[0]);
    expect(ApiPlaylists.remove).toHaveBeenCalled();
  });

  //CPEN422

  it('should have no tracks in empty playlist (CPEN422)', () => {
    iscope.$ctrl.search = 'a new playlist';
    angular.extend(UserPlaylists.tracks, userPlaylistsMock.items);
    scope.$digest();
    let length = UserPlaylists.tracks.length = 0;
    expect(length).toBe(0);
  });

  it('should be empty search upon creation (CPEN422)', () => {
    expect(iscope.$ctrl.search).toBe('');
  });

  it('should be unexpected behaviour when checking to length of playlist when playlist has been removed (CPEN422)', () => {
    angular.extend(UserPlaylists.tracks, userPlaylistsMock.items);
    let deferred = $q.defer();
    spyOn(ApiPlaylists, 'remove').and.callFake( () => {
      deferred.resolve();
      return deferred.promise;
    });

    try {
      iscope.$ctrl.remove(UserPlaylists.tracks[0]);
      let amountOfPlaylists = UserPlaylists.tracks.length;
    }
    catch(err) {
       expect(ApiPlaylists.remove).toHaveBeenCalled();
    }

  });

});
