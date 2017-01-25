'use strict';
import NavigatorModule, { NavigatorComponent } from './index.js';

describe('Navigator Component', () => {
  let controller, rootScope;

  beforeEach(window.module(NavigatorModule));
  beforeEach(window.inject(($componentController, $rootScope) => {
    rootScope = $rootScope;
    controller = $componentController(NavigatorComponent.selector, {
      $rootScope: rootScope
    });
  }));

  it('should update the active view when state changed', function() {
    const toState = { name: 'myPlaylists' };
    controller.handleStateChange({}, toState);
    const view = controller.views.find(view => view.ref === 'myPlaylists');
    expect(view.active).toBeTruthy();
  });

  it('should reset the old view to inactive state changed', function() {
    let toState = { name: 'myPlaylists' };
    controller.handleStateChange({}, toState);
    let view = controller.views.find(view => view.ref !== 'myPlaylists');
    expect(view.active).toBeFalsy();
  });

  it('(CPEN422) should update to Explore state', function() {
    const toState = { name: 'explore' };
    controller.handleStateChange({}, toState);
    const view = controller.views.find(view => view.ref === 'explore');
    expect(view.active).toBeTruthy();
  });

  it('(CPEN422) should update to myPlaylists state', function() {
    const toState = { name: 'myPlaylists' };
    controller.handleStateChange({}, toState);
    const view = controller.views.find(view => view.ref === 'myPlaylists');
    expect(view.active).toBeTruthy();
  });

});